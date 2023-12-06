package net.zithium.library.paper.items;

import net.kyori.adventure.text.Component;
import net.zithium.library.spigot.items.Base64Util;
import net.zithium.library.utils.Color;
import net.zithium.library.version.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"UnusedReturnValue", "ConstantConditions", "unused"})
public class ItemStackBuilder {

    private final ItemStack ITEM_STACK;

    public ItemStackBuilder(ItemStack item) {
        this.ITEM_STACK = item;
    }

    public ItemStackBuilder(Material material) {
        this.ITEM_STACK = new ItemStack(material);
    }

    public static ItemStackBuilder getItemStack(ConfigurationSection section, Player player) {
        ItemStack item = XMaterial.matchXMaterial(section.getString("material").toUpperCase()).get().parseItem();

        if (item.getType() == XMaterial.PLAYER_HEAD.parseMaterial() && section.contains("base64")) {
            item = Base64Util.getBaseHead(section.getString("base64")).clone();
        }

        ItemStackBuilder builder = new ItemStackBuilder(item);

        if (section.contains("amount")) {
            builder.withAmount(section.getInt("amount"));
        }

        if (section.contains("display_name")) {
            builder.withName(section.getString("display_name"));
        }

        if (section.contains("lore")) {
            builder.withLore(section.getStringList("lore"));
        }

        if (section.contains("glow") && section.getBoolean("glow")) {
            builder.withGlow();
        }

        if (section.contains("model_data")) {
            builder.withCustomData(section.getInt("model_data"));
        }


        if (section.contains("item_flags")) {
            List<ItemFlag> flags = new ArrayList<>();
            section.getStringList("item_flags").forEach(text -> {
                try {
                    ItemFlag flag = ItemFlag.valueOf(text);
                    flags.add(flag);
                } catch (IllegalArgumentException ignored) {
                }
            });
            builder.withFlags(flags.toArray(new ItemFlag[0]));
        }

        if (section.contains("potion_effect")) {
            ConfigurationSection potionSection = section.getConfigurationSection("potion_effect");
            String potionTypeName = potionSection.getString("type");
            PotionEffectType potionType = PotionEffectType.getByName(potionTypeName);

            if (potionType != null) {
                builder.withTippedArrowPotionEffect(potionType, 1, 1);
            } else {
                Bukkit.getServer().getLogger().warning("Invalid potion effect type: " + potionTypeName);
            }
        }

        return builder;
    }

    public static ItemStackBuilder getItemStack(ConfigurationSection section) {
        return getItemStack(section, null);
    }

    public ItemStackBuilder withAmount(int amount) {
        ITEM_STACK.setAmount(amount);
        return this;
    }

    public ItemStackBuilder withFlags(ItemFlag... flags) {
        ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.addItemFlags(flags);
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder withName(String name) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();

        if (ITEM_STACK.getType() == XMaterial.matchXMaterial(Material.AIR).parseMaterial()) {
            return this;
        }


        meta.displayName(Color.componentColor(name));
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder setSkullOwner(OfflinePlayer offlinePlayer) {
        try {
            SkullMeta im = (SkullMeta) ITEM_STACK.getItemMeta();
            im.setOwningPlayer(offlinePlayer);
            ITEM_STACK.setItemMeta(im);
        } catch (ClassCastException ignored) {
        }
        return this;
    }

    public ItemStackBuilder withLore(List<String> lore) {
        ItemMeta meta = this.ITEM_STACK.getItemMeta();
        List<Component> coloredLore = new ArrayList<>();


        if (ITEM_STACK.getType() == XMaterial.matchXMaterial(Material.AIR).parseMaterial()) {
            return this;
        }

        for (String s : lore) {
            coloredLore.add(Color.componentColor(s));  // Apply color to each lore line
        }

        meta.lore(coloredLore);
        this.ITEM_STACK.setItemMeta(meta);
        return this;
    }



    public ItemStackBuilder withCustomData(int data) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.setCustomModelData(data);
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder withGlow() {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ITEM_STACK.setItemMeta(meta);
        ITEM_STACK.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        return this;
    }

    public ItemStackBuilder withTippedArrowPotionEffect(PotionEffectType type, int duration, int amplifier) {
        if (ITEM_STACK.getType() == Material.TIPPED_ARROW) {
            // Create a potion with the desired effect
            PotionData potionData = new PotionData(PotionType.WATER);
            PotionMeta potionMeta = (PotionMeta) ITEM_STACK.getItemMeta();
            potionMeta.setBasePotionData(potionData);
            potionMeta.addCustomEffect(new PotionEffect(type, duration, amplifier), true);

            // Set the potion metadata to the tipped arrow
            ITEM_STACK.setItemMeta(potionMeta);
        }
        return this;
    }


    private String replace(String message, Object... replacements) {
        for (int i = 0; i < replacements.length; i += 2) {
            if (i + 1 >= replacements.length) break;
            message = message.replace(String.valueOf(replacements[i]), String.valueOf(replacements[i + 1]));
        }

        return message;
    }

    public ItemStack build() {
        return ITEM_STACK;
    }
}

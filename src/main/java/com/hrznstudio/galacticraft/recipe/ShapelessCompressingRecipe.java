/*
 * Copyright (c) 2020 HRZN LTD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.hrznstudio.galacticraft.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class ShapelessCompressingRecipe implements Recipe<Inventory> {
    private final Identifier id;

    //    private final String group;
    private final ItemStack output;
    private final DefaultedList<Ingredient> input;

    public ShapelessCompressingRecipe(Identifier id, /*String group, */ItemStack output, DefaultedList<Ingredient> input) {
        this.id = id;
//        this.group = group;
        this.output = output;
        this.input = input;
    }

    static ItemStack getStack(JsonObject json) {
        String itemId = JsonHelper.getString(json, "item");
        Item ingredientItem = Registry.ITEM.getOrEmpty(new Identifier(itemId)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + itemId + "'"));
        if (json.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        } else {
            int count = JsonHelper.getInt(json, "count", 1);
            return new ItemStack(ingredientItem, count);
        }
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return GalacticraftRecipes.SHAPELESS_COMPRESSING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return GalacticraftRecipes.SHAPELESS_COMPRESSING_TYPE;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public DefaultedList<Ingredient> getPreviewInputs() {
        return this.input;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        RecipeFinder recipeFinder_1 = new RecipeFinder();
        int int_1 = 0;

        for (int int_2 = 0; int_2 < inv.size(); ++int_2) {
            ItemStack stack = inv.getStack(int_2);
            if (!stack.isEmpty()) {
                ++int_1;
                recipeFinder_1.addItem(stack);
            }
        }

        return int_1 == this.input.size() && recipeFinder_1.findRecipe(this, null);
    }

    @Override
    public ItemStack craft(Inventory inv) {
        return this.output.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return width * height >= this.input.size();
    }

    public DefaultedList<Ingredient> getInput() {
        return this.input;
    }
}
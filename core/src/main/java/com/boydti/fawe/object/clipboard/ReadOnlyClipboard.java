package com.boydti.fawe.object.clipboard;

import com.boydti.fawe.jnbt.NBTStreamer;
import com.sk89q.jnbt.CompoundTag;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.entity.BaseEntity;
import com.sk89q.worldedit.entity.Entity;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.biome.BaseBiome;
import java.util.List;

public abstract class ReadOnlyClipboard extends FaweClipboard {
    public final Region region;

    public ReadOnlyClipboard(Region region) {
        this.region = region;
    }

    public static ReadOnlyClipboard of(final EditSession editSession, final Region region) {
        return new WorldCopyClipboard(editSession, region);
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public Vector getDimensions() {
        return region.getMaximumPoint().subtract(region.getMinimumPoint()).add(1, 1, 1);
    }

    @Override
    public void setDimensions(Vector dimensions) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public BaseBlock getBlock(int index) {
        throw new UnsupportedOperationException("World based clipboards do not provide index access");
    }

    @Override
    public BaseBiome getBiome(int index) {
        throw new UnsupportedOperationException("World based clipboards do not provide index access");
    }

    @Override
    public boolean setBiome(int x, int z, byte biome) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public void setBiome(int index, int biome) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public void streamBiomes(NBTStreamer.ByteReader task) {
        Vector dim = getDimensions();
        int index = 0;
        for (int z = 0; z <= dim.getBlockZ(); z++) {
            for (int x = 0; x <= dim.getBlockX(); x++, index++) {
                task.run(index, getBiome(x, z).getId());
            }
        }
    }

    @Override
    public abstract BaseBlock getBlock(int x, int y, int z);

    @Override
    public abstract BaseBiome getBiome(int x, int z);

    @Override
    public abstract List<? extends Entity> getEntities();

    @Override
    public boolean setBlock(int x, int y, int z, BaseBlock block) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public boolean setTile(int x, int y, int z, CompoundTag tag) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public Entity createEntity(Extent world, double x, double y, double z, float yaw, float pitch, BaseEntity entity) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public boolean remove(ClipboardEntity clipboardEntity) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public void setId(int index, int id) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public void setData(int index, int data) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }

    @Override
    public void setAdd(int index, int id) {
        throw new UnsupportedOperationException("Clipboard is immutable");
    }
}

package bernie.software.gui.surge;

import bernie.software.entity.SurgeVehicle;
import bernie.software.gui.AbstractInventoryEntity;
import bernie.software.gui.VehicleContainer;
import bernie.software.gui.VehicleContainerTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class SurgeContainer extends VehicleContainer {

    public SurgeContainer(final int id, final PlayerInventory playerInv, final AbstractInventoryEntity entity) {
        super(VehicleContainerTypes.SURGE.get(), id, entity);

        //Surge Inventory
        for(int r = 0;r < 3; r++){
            for(int c = 0;c < 9;c++){
                if(c < 3) {
                    this.addSlot(new SlotItemHandler(this.inventory, r * 9 + c, 8 + c * 18, 18 + r * 18));
                }
                else if(c > 4 && r == 0){
                    this.addSlot(new SlotItemHandler(this.inventory, r * 9 + c, 8 + c * 18, 18 + r * 18));
                }
                else if(c == 8 && r == 2){
                    this.addSlot(new SlotItemHandler(this.inventory, r * 9 + c, 8 + c * 18, 18 + r * 18));
                }
            }
        }

        //Player Inventory
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInv, l + k * 9 + 9, 8 + l * 18, 83 + k * 18));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInv, x, 8 + x * 18, 141));
        }
    }

    public SurgeContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, (AbstractInventoryEntity) playerInventory.player.world.getEntityByID(data.readInt()));
    }
}

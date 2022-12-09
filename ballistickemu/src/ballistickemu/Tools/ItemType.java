/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ballistickemu.Tools;

/**
 *
 * @author Simon
 */
public class ItemType {
    public static int getItemType(int itemID)
    {
        int itemType = 0; //bad item

        if ((itemID >= 200) && (itemID < 240))
            itemType = 2; //pet
        else if ((itemID >= 100) && (itemID < 200))
            itemType = 1; //spinner
        return itemType;
    }
}

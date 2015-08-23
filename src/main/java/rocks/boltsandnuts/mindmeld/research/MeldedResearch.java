package rocks.boltsandnuts.mindmeld.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import rocks.boltsandnuts.mindmeld.ModInformation;
import rocks.boltsandnuts.mindmeld.util.TextHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

/**
 * Created by downslope7 on 4/7/15.
 */
public class MeldedResearch extends ResearchItem {
    public MeldedResearch(String key, String category) {
        super(key,category);
    }
    public MeldedResearch(String key, String category, AspectList tags, int col, int row, int complex, ResourceLocation icon) {
        super(key, category, tags, col, row, complex, icon);
    }

    public MeldedResearch(String key, String category, AspectList tags, int col, int row, int complex, ItemStack icon) {
        super(key, category, tags, col, row, complex, icon);
    }

    @Override
    public String getName() {
        return TextHelper.localize(ModInformation.ID + ".research.name." + key);
    }

    @Override
    public String getText() {
        return TextHelper.localize(ModInformation.ID + ".research.tag." + key);
    }

    @Override
    public ResearchItem setPages(ResearchPage... par) {
        for (ResearchPage page : par) {
            if (page.type == ResearchPage.PageType.TEXT) {
                page.text = ModInformation.ID + ".research.page." + key + "." + page.text;
            }
            if (page.type == ResearchPage.PageType.INFUSION_CRAFTING) {
                if (parentsHidden == null || parentsHidden.length == 0) {
                    parentsHidden = new String[]{"INFUSION"};
                } else {
                    String[] newParents = new String[parentsHidden.length + 1];
                    newParents[0] = "INFUSION";
                    for (int i = 0; i < parentsHidden.length; i++) {
                        newParents[i + 1] = parentsHidden[i];
                    }
                    parentsHidden = newParents;
                }
            }
        }
        return super.setPages(par);
    }

}

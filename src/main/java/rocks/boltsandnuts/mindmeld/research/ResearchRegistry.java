package rocks.boltsandnuts.mindmeld.research;


import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import rocks.boltsandnuts.mindmeld.items.*;
import thaumcraft.common.items.ItemZombieBrain;
import thaumcraft.common.items.relics.ItemThaumonomicon;

/**
 * Created by downslope7 on 4/7/15.
 */
@SuppressWarnings("unused")
public class ResearchRegistry{

    private static ItemMeldingManuscript itemMeldingManuscript;
    private static ShapelessArcaneRecipe meldingManuscriptRecipe;
    private static ResearchPage researchPage1;

    public static void registerResearch() {

        //Melding Manuscript
        AspectList meldingManuscriptAspects = new AspectList();
        meldingManuscriptAspects.add(Aspect.ENERGY, 5);
        meldingManuscriptAspects.add(Aspect.MIND, 8);
        meldingManuscriptAspects.add(Aspect.MAGIC, 6);
        meldingManuscriptAspects.add(Aspect.EXCHANGE, 10);

        AspectList meldingVisCost = new AspectList();
        meldingVisCost.add(Aspect.FIRE, 50);
        meldingVisCost.add(Aspect.WATER, 50);
        meldingVisCost.add(Aspect.EARTH, 50);
        meldingVisCost.add(Aspect.ORDER, 50);
        meldingVisCost.add(Aspect.AIR, 50);
        meldingVisCost.add(Aspect.ENTROPY, 50);


        itemMeldingManuscript = new ItemMeldingManuscript();
        ItemThaumonomicon itemThaumonomicon = new ItemThaumonomicon();
        ItemZombieBrain itemZombieBrain = new ItemZombieBrain();

/*        meldingManuscriptRecipe = ThaumcraftApi.addShapelessArcaneCraftingRecipe( "MINDMELD",
                new ItemStack(itemMeldingManuscript),
                meldingVisCost,
                "tBt", "", "",
                't', itemThaumonomicon,
                'B', itemZombieBrain);
This is an invalid recipe, causes crashy crash
*/
        //ArrayList<ResearchPage> pages = new ArrayList<ResearchPage>();
        researchPage1 = new ResearchPage("RESEARCHDUPE", "MINDMELD.1");

        ResearchItem researchItem = new ResearchItem(   "MINDMELD",
                "MINDMELD",
                meldingManuscriptAspects,
                -2, -3, 1,
                new ItemStack(itemMeldingManuscript) );

        //researchItem.setPages(researchPage1, new ResearchPage((IArcaneRecipe) meldingManuscriptRecipe));
        //researchItem.setParentsHidden("RESEARCHDUPE").registerResearchItem();
        
        /*
        pages.add(new ResearchPage("tc.research_name.RESEARCHDUPE","tc.research_page.MINDMELD"));
        
        ResourceLocation texture = new ResourceLocation(ModInformation.ID, "textures/items/meldingManuscript.png");
        ResearchCategories.registerCategory("MINDMELD", texture, new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

        new ResearchItem("MINDMELD", "MINDMELD", meldingVisCost, 0, 0, 1, texture).setPages(pages.toArray(new ResearchPage[pages.size()])).setSecondary().setParents("MINDMELD").registerResearchItem();
        */
    }

}

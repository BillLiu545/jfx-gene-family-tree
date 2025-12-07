import javafx.collections.*;
import javafx.scene.control.*;
import java.util.*;
public class GeneticFamily extends TreeMap<Integer, String>
{
    private final int NUM_BASES = 10;
    private final String[] bases = {"A","T","C","G"};
    private int curr_num = 0;
    public void addMember()
    {
        curr_num++;
        int ind = curr_num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_BASES; i++)
        {
            Random rand = new Random();
            int rand_ind = rand.nextInt(bases.length);
            sb.append(bases[rand_ind]);
        }
        put(ind, sb.toString());
    }
    
    public void remove_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remove Family Tree Member");
        dialog.setHeaderText("Remove Family Tree Member");
        dialog.setContentText("Please enter Family Tree Member Number to remove: ");
        Optional<String> opt = dialog.showAndWait();
        Integer removed_ind = null;
        try
        {
            removed_ind = Integer.parseInt(opt.get());
        }
        catch (Exception ex)
        {
            removed_ind = 0;
        }
        removeMember(removed_ind);
    }
    
    public void removeMember(int member)
    {
        remove(member);
    }
    
    public void mutate_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mutate Family Tree Member");
        dialog.setHeaderText("Mutate Family Tree Member");
        dialog.setContentText("Please enter Family Tree Member Number to mutate: ");
        Optional<String> opt = dialog.showAndWait();
        Integer mutated = null;
        try
        {
            mutated = Integer.parseInt(opt.get());
        }
        catch (Exception ex)
        {
            mutated = 0;
        }
        mutate(mutated);
    }
    
    public void mutate(int member)
    {
        Set<Map.Entry<Integer, String>> set = entrySet();
        Iterator<Map.Entry<Integer,String>> iter = set.iterator();
        while (iter.hasNext())
        {
            Map.Entry<Integer,String> next = iter.next();
            if (next.getKey() == member)
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < NUM_BASES; i++)
                {
                    Random rand = new Random();
                    int rand_ind = rand.nextInt(bases.length);
                    sb.append(bases[rand_ind]);
                }
                next.setValue(sb.toString());
                break;
            }
        }
    }
    
    public String toString()
    {
        if (isEmpty())
        {
            return "NONE";
        }
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Integer, String>> set = entrySet();
        Iterator<Map.Entry<Integer,String>> iter = set.iterator();
        while (iter.hasNext())
        {
            Map.Entry<Integer,String> next = iter.next();
            sb.append("{" + next.getKey() + " -> " + next.getValue() + "} -> ");
        }
        sb.append(" END");
        return sb.toString();
    }
    
    public String relation_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Compare Family Tree Members");
        dialog.setHeaderText("COmpare Family Tree Members");
        dialog.setContentText("Please enter Family Tree Member Number to Compare: ");
        Optional<String> opt = dialog.showAndWait();
        Integer one = null;
        try
        {
            one = Integer.parseInt(opt.get());
        }
        catch (Exception ex)
        {
            one = 0;
        }
        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Compare Family Tree Member");
        dialog2.setHeaderText("Compare Family Tree Member");
        dialog2.setContentText("Please enter Family Tree Member Number to Compare: ");
        Optional<String> opt2 = dialog2.showAndWait();
        Integer two = null;
        try
        {
            two = Integer.parseInt(opt2.get());
        }
        catch (Exception ex)
        {
            two = 0;
        }
        return relation(one, two);
    }
    
    public String relation(int one, int other)
    {
        try {
            String oneString = get(one);
            String otherString = get(other);
            double same = 0;
            for (int i = 0; i < otherString.length(); i++)
            {
                if (otherString.charAt(i) == oneString.charAt(i))
                {
                    same++;
                }
            }
            double percent = (same/oneString.length()) * 100;
            return "Member " + other + " has " + percent + "% same composition with Member " + one;
        }
        catch (Exception ex)
        {
            return "Comparison does not exist for these two members.";
        }
    }
    
    public static void main(String[] args)
    {
        GeneticFamily family = new GeneticFamily();
        family.addMember();
        family.addMember();
        System.out.println(family);
        family.removeMember(1);
        System.out.println(family);
        family.removeMember(1);
        family.addMember();
        System.out.println(family);
        family.addMember();
        System.out.println(family);
        family.removeMember(1);
        family.removeMember(3);
        System.out.println(family);
        System.out.println(family.relation(2,4));
        family.mutate(2);
        System.out.println(family);
        System.out.println(family.relation(2,4));
    }
}

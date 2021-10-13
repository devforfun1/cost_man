package tool;

import java.util.ArrayList;
import java.util.List;

public class ListTool<T> {


    public ListTool() {
    }

    // Method from https://stackoverflow.com/questions/29225041/find-all-combinations-in-arraylist-recursively
    public List<List<T>> FindAllCombinationsWithUniqueElements(List<T> input,
                                                               int k,
                                                               List<List<T>> result) {

        if (k == 0) {
            return result;
        }


        if (result.size() == 0) {
            for (T i : input) {
                ArrayList<T> subList = new ArrayList<>();
                if (!subList.contains(i))
                    subList.add(i);
                result.add(subList);
            }

            return FindAllCombinationsWithUniqueElements(input, k - 1, result);
        }


        List<List<T>> newResult = new ArrayList<>();
        for (List<T> subList : result) {
            for (T i : input) {
                List<T> newSubList = new ArrayList<>();
                newSubList.addAll(subList);
                if (!newSubList.contains(i))
                    newSubList.add(i);
                newResult.add(newSubList);
            }
        }

        return FindAllCombinationsWithUniqueElements(input, k - 1, newResult);
    }
}

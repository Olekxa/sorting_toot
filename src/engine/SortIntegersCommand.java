//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package engine;

import data.Data;
import utils.SortType;

public class SortIntegersCommand extends Command {

    public SortIntegersCommand(Data input, SortType sortType) {
        super(input, sortType);
    }

    @Override
    public String execute() {
        return "no such method";
    }
}



//    public String execute() {
//        List<Integer> innStream = this.getList();
//        int countItems = innStream.size();
//        StringBuilder listAsString = new StringBuilder();
//        for (Integer integer : innStream) {
//            listAsString.append(" ").append(integer.toString());
//        }
//        List<String> collect = fromLongToString(innStream);
//        getInputData().setList(collect);
//        return String.format("Total numbers: %d.\nSorted data: %s", countItems, listAsString.toString().trim());
//    }
//    private List<String> fromLongToString(List<Integer> innStream) {
//        return  innStream.stream()
//                .map(Object::toString)
//                .collect(Collectors.toList());
//    }
//
//    private List<Integer> getList() {
//        return this.getInputData()
//                .getList()
//                .stream()
//                .map(String::trim)
//                .flatMap((s) -> Arrays.stream(s.split("[a-zA-Z]+|\\s")))
//                .filter((x) -> x.matches("-?\\d+(\\.\\d+)?"))
//                .map(Integer::parseInt)
//                .sorted()
//                .toList();
//    }
//}

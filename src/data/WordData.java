package data;

import item.IWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WordData extends Data<IWord> {
    public WordData(File input) throws FileNotFoundException {
        super(input);
    }

    @Override
    public List<IWord> mapData(List<String> data) {
        return data
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> !x.equals(" "))
                .filter(x -> !x.equals(""))
                .map(IWord::new)
                .sorted(Comparator.comparing(IWord::getValue))
                .toList();
    }
}

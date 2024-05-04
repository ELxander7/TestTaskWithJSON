package ru.itis.inf304.lab7;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Deque;
import java.util.LinkedList;



public class Method3 {
    public static boolean isValidJSONFile(String fileName) throws IOException, ParseException, Exception {
        if (Objects.equals(fileName,"")) throw new NullPointerException("Имя файла не можем быть пустым");

        String fileContent = readJsonFile(fileName);
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put('}', '{');
        brackets.put(']', '[');

        Deque<Character> stack = new LinkedList<>();
        int position = 0;

        for (char c: fileContent.toCharArray()){
            if (!isTextSymbol(c)) throw new Exception("Файл не текстовый");
            position++;

            if (brackets.containsValue(c)) {
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != brackets.get(c)){
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }
    public static String readJsonFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object;
        try {
            object = (JSONObject) parser.parse(new FileReader(fileName));
            return object.get("String").toString();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден");
        }
    }
    public static boolean isTextSymbol(char symbol) {
        return String.valueOf(symbol).matches("^[A-Za-zА-Яа-яЁё0-9\\{\\}\\[\\].,:;\"\\r\\n ]$");
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {


            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long underage;
        underage = persons.stream()
                .filter(person -> person.getAge() < 18)
                .mapToInt(Person::getAge)
                .count();
        System.out.println("В городе " + underage + " несовершеннолетних.");
        List<String> recruits = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() < 27)
                .filter(person -> person.getAge() > 18)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("В городе " + recruits.size() + " призывников,один из них " + recruits.get(200));
        List<Person> personWorks = persons.stream()
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex() == Sex.MAN || person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("В городе " + personWorks.size() + " работающих,вот например " + personWorks.get(50));

    }
}
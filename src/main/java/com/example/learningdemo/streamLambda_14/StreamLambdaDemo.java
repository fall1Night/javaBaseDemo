package com.example.learningdemo.streamLambda_14;

import com.example.learningdemo.pojo.Person;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Stream API & Lambda 表达式
 * <p>
 * 一、Lambda表达式
 * Lambda表达式是Java 8引入的一种简洁的匿名函数写法，可以替代匿名内部类。
 * 语法：(参数) -> { 表达式/语句 }
 * <p>
 * 二、函数式接口
 * 只有一个抽象方法的接口称为函数式接口，可以使用@FunctionalInterface注解标识。
 * Java 8提供了大量内置函数式接口：
 * - Consumer<T> : 消费型接口，接收参数无返回值
 * - Supplier<T> : 供给型接口，无参数有返回值
 * - Function<T,R> : 函数型接口，接收参数有返回值
 * - Predicate<T> : 断言型接口，接收参数返回boolean
 * <p>
 * 三、Stream API
 * Stream是Java 8处理集合的关键抽象概念，它可以指定对集合进行的操作。
 * Stream操作分为中间操作和终端操作：
 * - 中间操作：返回新Stream，可以链式调用（filter, map, sorted等）
 * - 终端操作：返回结果或副作用（forEach, collect, reduce, count等）
 * <p>
 * 四、Optional
 * Optional类是一个容器类，用于表示一个值存在或不存在，避免空指针异常。
 */
public class StreamLambdaDemo {

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(
                new Person("张三", 28, "男", 8500),
                new Person("李四", 35, "男", 12000),
                new Person("王五", 22, "女", 6500),
                new Person("赵六", 40, "男", 15000),
                new Person("钱七", 26, "女", 7800),
                new Person("孙八", 32, "男", 10000)
        );

        System.out.println("===== 1. Lambda表达式基础 =====");
        lambdaBasic();

        System.out.println("\n===== 2. 函数式接口 =====");
        functionalInterfaceDemo();

        System.out.println("\n===== 3. Stream中间操作 =====");
        streamIntermediateOperations(personList);

        System.out.println("\n===== 4. Stream终端操作 =====");
        streamTerminalOperations(personList);

        System.out.println("\n===== 5. Stream聚合与分组 =====");
        streamGrouping(personList);

        System.out.println("\n===== 6. Parallel Stream并行流 =====");
        parallelStreamDemo();

        System.out.println("\n===== 7. Optional使用 =====");
        optionalDemo(personList);

        System.out.println("\n===== 8. 方法引用 =====");
        methodReferenceDemo(personList);
    }

    /**
     * Lambda表达式基础演示
     */
    private static void lambdaBasic() {
        Runnable runnable = () -> System.out.println("Lambda Runnable");
        runnable.run();

        Comparator<Integer> comparator = (a, b) -> a - b;
        System.out.println("比较结果: " + comparator.compare(5, 3));

        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("加法结果: " + add.apply(10, 20));
    }

    /**
     * 函数式接口演示
     */
    private static void functionalInterfaceDemo() {
        Consumer<String> consumer = s -> System.out.println("消费: " + s);
        consumer.accept("Hello Lambda");

        Supplier<Integer> supplier = () -> (int) (Math.random() * 100);
        System.out.println("供给随机数: " + supplier.get());

        Function<String, Integer> function = s -> s.length();
        System.out.println("字符串长度: " + function.apply("Hello"));

        Predicate<Integer> predicate = num -> num > 10;
        System.out.println("15 > 10 ? " + predicate.test(15));
    }

    /**
     * Stream中间操作演示
     */
    private static void streamIntermediateOperations(List<Person> personList) {
        System.out.println("过滤年龄大于30的人:");
        personList.stream()
                .filter(p -> p.getAge() > 30)
                .forEach(p -> System.out.println(p.getName()));

        System.out.println("\n获取所有人姓名:");
        personList.stream()
                .map(Person::getName)
                .forEach(System.out::println);

        System.out.println("\n按年龄排序(升序):");
        personList.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(p -> System.out.println(p.getName() + " - " + p.getAge()));

        System.out.println("\n跳过前2个，取3个:");
        personList.stream()
                .skip(2)
                .limit(3)
                .forEach(p -> System.out.println(p.getName()));

        System.out.println("\n去重(需要equals支持):");
        List<Person> dupList = new ArrayList<>(personList);
        dupList.add(new Person("张三", 28, "男", 8500));
        dupList.stream()
                .distinct()
                .forEach(p -> System.out.println(p.getName()));
    }

    /**
     * Stream终端操作演示
     */
    private static void streamTerminalOperations(List<Person> personList) {
        long count = personList.stream()
                .filter(p -> p.getSex().equals("男"))
                .count();
        System.out.println("男性人数: " + count);

        Optional<Person> maxSalary = personList.stream()
                .max(Comparator.comparingInt(Person::getSalary));
        maxSalary.ifPresent(p -> System.out.println("最高薪资: " + p.getName() + " - " + p.getSalary()));

        boolean allAdult = personList.stream()
                .allMatch(p -> p.getAge() >= 18);
        System.out.println("是否都成年: " + allAdult);

        boolean hasHighSalary = personList.stream()
                .anyMatch(p -> p.getSalary() > 12000);
        System.out.println("是否有高薪人士: " + hasHighSalary);

        int totalSalary = personList.stream()
                .mapToInt(Person::getSalary)
                .sum();
        System.out.println("总薪资: " + totalSalary);

        Optional<Integer> sumOptional = personList.stream()
                .map(Person::getSalary)
                .reduce(Integer::sum);
        sumOptional.ifPresent(sum -> System.out.println("薪资总和(reduce): " + sum));
    }

    /**
     * Stream聚合与分组演示
     */
    private static void streamGrouping(List<Person> personList) {
        Map<String, List<Person>> groupBySex = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex));
        System.out.println("按性别分组:");
        groupBySex.forEach((sex, persons) -> {
            System.out.println(sex + ": " + persons.stream().map(Person::getName).collect(Collectors.toList()));
        });

        Map<String, Long> countBySex = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex, Collectors.counting()));
        System.out.println("\n按性别计数: " + countBySex);

        Map<String, Integer> sumSalaryBySex = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex,
                        Collectors.summingInt(Person::getSalary)));
        System.out.println("按性别薪资总和: " + sumSalaryBySex);

        List<String> names = personList.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("姓名列表: " + names);

        String nameStr = personList.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("姓名拼接: " + nameStr);
    }

    /**
     * 并行流演示
     */
    private static void parallelStreamDemo() {
        List<Integer> numbers = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());

        long start = System.currentTimeMillis();
        int sumSequential = numbers.stream().reduce(0, Integer::sum);
        long sequentialTime = System.currentTimeMillis() - start;
        System.out.println("串行求和结果: " + sumSequential + ", 耗时: " + sequentialTime + "ms");

        start = System.currentTimeMillis();
        int sumParallel = numbers.parallelStream().reduce(0, Integer::sum);
        long parallelTime = System.currentTimeMillis() - start;
        System.out.println("并行求和结果: " + sumParallel + ", 耗时: " + parallelTime + "ms");

        System.out.println("并行流处理顺序(可能无序):");
        numbers.stream().limit(5).forEach(n -> System.out.print(n + " "));
        System.out.println();
        numbers.parallelStream().limit(5).forEach(n -> System.out.print(n + " "));
    }

    /**
     * Optional使用演示
     */
    private static void optionalDemo(List<Person> personList) {
        Optional<Person> person = personList.stream()
                .filter(p -> p.getName().equals("不存在的人"))
                .findFirst();

        person.ifPresentOrElse(
                p -> System.out.println("找到: " + p.getName()),
                () -> System.out.println("未找到该人")
        );

        String name = personList.stream()
                .filter(p -> p.getName().equals("李四"))
                .findFirst()
                .map(Person::getName)
                .orElse("默认姓名");
        System.out.println("姓名: " + name);

        Optional<String> upperName = Optional.ofNullable("hello")
                .map(String::toUpperCase);
        upperName.ifPresent(n -> System.out.println("大写姓名: " + n));

        int age = personList.stream()
                .filter(p -> p.getName().equals("王五"))
                .findFirst()
                .map(Person::getAge)
                .orElse(0);
        System.out.println("王五的年龄: " + age);
    }

    /**
     * 方法引用演示
     */
    private static void methodReferenceDemo(List<Person> personList) {
        System.out.println("静态方法引用:");
        List<String> upperNames = personList.stream()
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperNames);

        System.out.println("\n实例方法引用:");
        StringJoiner joiner = new StringJoiner("; ");
        personList.stream()
                .map(Person::getName)
                .forEach(joiner::add);
        System.out.println(joiner.toString());

        System.out.println("\n构造方法引用:");
        List<String> names = Arrays.asList("Tom", "Jerry", "Mike");
        List<Person> newPersons = names.stream()
                .map(name -> new Person(name, 20, "男", 5000))
                .collect(Collectors.toList());
        newPersons.forEach(p -> System.out.println(p.getName()));
    }
}
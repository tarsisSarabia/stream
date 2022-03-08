package aplication;

import entities.Employee;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a full path: ");
        String path = sc.nextLine();
        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> emp = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                emp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line=br.readLine();

            }
            System.out.println("Informe o valor do salário para o filtro: ");
            double salary = sc.nextDouble();
            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
            List<String> email = emp.stream()
                    .filter(x -> x.getSalary() > salary)
                    .map(s1 -> s1.getEmail()).sorted(comp.reversed())
                    .collect(Collectors.toList());
            System.out.println("Email of people whose salary is more than 2000.00:");
            email.forEach(System.out::println);

            

            Double sum = emp.stream()
                    .filter(p -> p.getName().charAt(0) == 'M')
                    .map(Employee::getSalary)
                    .reduce(0.00, (x, y) -> x + y);
            System.out.printf("Sum of salary of people whose name starts with 'M':" +String.format("%.2f", sum) );

        } catch (Exception e) {
        }
    }

}

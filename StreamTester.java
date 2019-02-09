
import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.nio.*;
import java.io.*;

public class StreamTester {
	
	public static void main (String[] args) throws IOException {
		
		//#1. Integer Stream :: 123456789
		IntStream
			.range(1, 10)
				.forEach(System.out::print);
		System.out.println ();
		
		
		//#2. Integer Stream with skip :: 6789
		IntStream
			.range(1, 10)
				.skip(5)
					//.forEach(System.out::print);
					.forEach(x -> System.out.print(x));
		System.out.println ();
		
		
		//#3. Integer Stream with sum :: 10
		int value = IntStream
			.range(1, 5)
				.sum();
		System.out.println (value);
		
		
		//#4. Stream.of with sorted, findFirst and ifPresent :: Alberto
		Stream.of("Ava","Aneri","Alberto")
			.sorted()
				.findFirst()
					.ifPresent(System.out::println);
					
					
		//#5. Arrays.stream with filter, sorted, map and forEach :: Adedayo Adewale
		String names[] = {"Kunle","Habeeb","Tunde","Adedayo","Fatimah","Muhammad","Maryam","Boluwatife","Demola","Adewale"};
		Arrays.stream(names)
			.filter(x -> x.startsWith("A"))
				.sorted()
					.map(a -> a+" ")
						.forEach(System.out::print);
		System.out.println ();
						
						
		//#6. Arrays.stream with map, average and ifPresent :: 44.0
		int values[] = {2,4,6,8,10};
		Arrays.stream(values)
			.map(x -> x * x)
				.average()
					.ifPresent(System.out::println);
					
					
		//#7. Collections.stream with filter, sorted, map and forEach :: muhammadmaryam
		List<String> people = Arrays.asList("Kunle","Habeeb","Tunde","Adedayo","Fatimah","Muhammad","Maryam","Boluwatife","Demola","Adewale");
		people.stream()
			.map(String::toLowerCase)
				.filter(x -> x.startsWith("m"))
					.forEach(System.out::print);
		System.out.println ();
		
		
		//#8. Stream from text file, sorted, filter and print :: AdedayoAdewaleBoluwatifeFatimahMuhammad
		Stream<String> person = Files.lines(Paths.get("names.txt"));
		person
			.sorted()
				.filter(x -> x.length() > 6)
					.forEach(System.out::print);
		person.close();
		System.out.println ();
		
		
		//#9. Stream List from text file, filter, collect and print :: KunleHabeebTundeAdedayoBoluwatifeDemolaAdewale
		List<String> person2 = Files.lines(Paths.get("names.txt"))
			.filter(x -> x.contains("e"))
				.collect(Collectors.toList());
		person2.forEach(x -> System.out.print (x));
		System.out.println ();
		
		
		//#10. Stream rows from text file, map, filter, count and print :: 5 rows
		Stream<String> data1 = Files.lines(Paths.get("data.txt"));
		int rowCount = (int) data1
			.map(x -> x.split(","))
				.filter(x -> x.length == 3)
					.count();
		System.out.println (rowCount + " rows");
		data1.close();
		
		
		//#11. Stream rows from text file, map, filter, parse data :: B 17 2.8   D 23 2.7   F 18 3.4
		Stream<String> data2 = Files.lines(Paths.get("data.txt"));
		data2
			.map(x -> x.split(","))
				.filter(x -> x.length == 3)
					.filter(x -> Integer.parseInt(x[1]) > 15)
						.forEach(x -> System.out.println (x[0] +" "+x[1]+" "+x[2]));
		data2.close();
		
		
		//#12. Stream rows from text file, map, filter, store field in HashMap :: {B=17, D=23, F=18}
		Stream<String> data3 = Files.lines(Paths.get("data.txt"));
		Map<String, Integer> map = new HashMap<>();
		map = data3
			.map(x -> x.split(","))
				.filter(x -> x.length == 3)
					.filter(x -> Integer.parseInt(x[1]) > 15)
						.collect(Collectors.toMap(
							x -> x[0], x -> Integer.parseInt(x[1])
							));
		data3.close();
		System.out.println (map);
		
		
		//#13. Reduction to sum  ::  13.600000000000001
		double total = Stream.of(7.3,1.5,4.8)
			.reduce(0.0, (a, b) -> a + b);
		System.out.println (total);
		
		
		//#14. Reduction - summary statistics  :: IntSummaryStatistics{count=7, sum=203, min=2, average=29.000000, max=88}
		IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
			.summaryStatistics();
		System.out.println (summary);
	}
}
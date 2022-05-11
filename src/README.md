Sorting Tool

In the modern world, data has become so abundant that processing it is no easy business. How can anyone make sense of all these words and numbers? In this project, you will write a program that processes textual and numeric data and sorts it. Your program will be able to determine the biggest or most frequent pieces of data and perform the necessary calculations on them. Data is waiting to be sorted!

Stage 1:
With this project, you will learn how to process numeric and text input, sort it, and output useful information about the data. Your final program will work with numbers, words, and lines. In the first stage, we will stick to integer numbers.

The program should read user input consisting of several lines, each containing integers separated by an arbitrary number of spaces. Then it should count the number of integers in the input, find the greatest one, and identify the number of times this integer appears. Finally, it should print this information to the console.

If you run your program and try to type in the numbers manually, you'll see that this process will go on infinitely. To end the input, the user should type the end-of-file symbol, informing the operating system that no more input will be provided. On Linux and Mac, the shortcut for this symbol is Ctrl+D or Cmd+D, and on Windows the combination is Ctrl+Z. You don't need to check specifically for the end-of-file symbols in your program, use scanner.hasNext... instead. This will return false if the end of the input is reached.

Stage 2:
Remember how we wanted the program to work not only with numbers but also with lines and words? In this stage, you will add behavior for text data types to your program. You will also implement parsing for command-line arguments that will allow the user to define the input data type

After parsing the arguments and reading the input, the program should treat the input according to its data type and output an information message similar to the one from the previous stage.
Stage 3:

This project is called Sorting Tool, but, so far, you still haven’t really sorted the elements of the user input. Let's add a number-sorting mechanism to the program and provide an appropriate command-line argument to use this function.

The new optional -sortIntegers argument indicates that the input numbers should be sorted.
Stage 4:
Now that it’s possible to sort numbers, it's time to implement the same functionality for words and lines. But that's not all for this stage! Let's also add a new sorting type that is often rather useful: sorting by count, a type of sorting that arranges the elements by number of occurrences.

Instead of -sortIntegers, we will use the universal -sortingType argument.

The result of sorting by count should be pairs (count, dataEntry) sorted by the count value.

Note that from this stage on, your program focuses on sorting user data, so it will no longer output information about the greatest number or the longest string.

Stage 5:
There is always a possibility that someone will run your program the wrong way. It shouldn't just silently crash, but instead, it should print a message that informs the user of the mistake they made.

In this stage, let's implement error handling for various exceptional situations the user might encounter.
Stage 6:
Sometimes it's useful to read data that is from a file, rather than from the standard input, and write the result to another file instead of printing it to the console. Add this functionality to your program along with the appropriate command-line argument support.
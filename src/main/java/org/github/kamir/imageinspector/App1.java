package org.github.kamir.imageinspector;

/**
 * Hello world!
 *
 */
public class App1
{
    public static void main( String[] args )
    {
        System.out.println( "Example 1" );
        
        Example1.run();
        
        System.out.println( "Example 2" );
        
        Example2.run();
        
        System.out.println( "Example 3" );

        String input1 = "/GITHUB/StatisticalImageAnalyzer/data/in/stack-tiff";
        Example3.run_on_TIFF(input1);

        String input2 = "/GITHUB/StatisticalImageAnalyzer/data/in/stack-png";
        Example3.run_on_PNG(input2);

    }
}

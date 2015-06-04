package test.runner;

import java.io.PrintWriter;

import javax.swing.JOptionPane;

import test.runner.ITest;

/**
 * A simple servlet to run the JDBC tests
 */
public class MainRunner {

  public static void main(String argv[]) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(java.lang.System.out);
      //if we have launched the program without arguments, we ask for them
      if(argv.length == 0)
      {
    	  ITest.Runner.runTest("org.cozy.plug.Plug", pw, "/dev/ttyACM0");
      }
      else if(argv.length == 1)
      {
    	  ITest.Runner.runTest("org.cozy.plug.Plug", pw, argv[0]);
      }
      //      Tearing tests need another parameter
      else if (argv.length == 3){
    	  short partOfTheCode = Short.parseShort(argv[2]);
    	  ITestTearing.Runner.runTest(argv[0], pw, argv[1], partOfTheCode);
      }else{
    	  ITest.Runner.runTest(argv[0], pw, argv[1]);
      }
    } finally {
      if (pw != null) {
        pw.close();
      }
    }
  }

}
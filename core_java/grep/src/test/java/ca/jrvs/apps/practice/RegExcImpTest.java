package ca.jrvs.apps.practice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegExcImpTest {

  static RegexExcImp tester;

  @BeforeClass
  public static void testSetup() {
    tester = new RegexExcImp();
  }

  @AfterClass
  public static void testCleanup() {
    // Do your cleanup here like close URL connection , releasing resources etc
  }

  @Test
  public void testMatchJpeg() {
    assertTrue(tester.matchJpeg("abc.jpg"));
    assertTrue(tester.matchJpeg("abc.jpeg"));
    assertFalse(tester.matchJpeg(".jpg"));
    assertFalse(tester.matchJpeg(".jpeg"));
    assertFalse(tester.matchJpeg("abc.jpggx"));
    assertFalse(tester.matchJpeg("jpg"));
    assertFalse(tester.matchJpeg("jpeg"));
  }

  @Test
  public void testMatchCsv() {
    assertTrue(tester.matchCsv("myfile.csv"));
    assertTrue(tester.matchCsv("my-file.csv"));
    assertFalse(tester.matchCsv("myfile.csvx"));
    assertFalse(tester.matchCsv("myfile_csv"));
    assertFalse(tester.matchCsv(".csv"));
    assertFalse(tester.matchCsv("csv"));
  }

  @Test
  public void testMatchIp() {
    assertTrue(tester.matchIp("192.16.0.1"));
    assertTrue(tester.matchIp("182.168.100.100"));
    assertFalse(tester.matchIp("192.168"));
    assertFalse(tester.matchIp("192#168#0#1"));
  }

  @Test
  public void testMatchEmpty() {
    assertTrue(tester.isEmptyLine(""));
    assertTrue(tester.isEmptyLine("  "));
    assertTrue(tester.isEmptyLine(" "));
    assertFalse(tester.isEmptyLine("   a   "));
    assertFalse(tester.isEmptyLine("a"));
  }
}

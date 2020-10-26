package com.capgemini.indianstatescensusanalyser;

import com.capgemini.indianstatescensusanalysercom.exception.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class StateCensusAnalyserTest
{
    private static String DATA_CSV_FILE_PATH = "C:\\Users\\abhij\\eclipse-workspace\\IndianStatesCensusAnalyser\\src\\test\\java\\resources\\StateCensusData.csv";
    private static String IMPROPER_FILE_NAME = "C:\\Users\\abhij\\eclipse-workspace\\IndianStatesCensusAnalyser\\src\\test\\java\\resources\\StateCensusData1.csv";
    private static String IMPROPER_FILE_TYPE = "C:\\Users\\abhij\\eclipse-workspace\\IndianStatesCensusAnalyser\\src\\test\\java\\resources\\StateCensusData.txt";
    private static String WRONG_DELIMITER1="C:\\Users\\abhij\\eclipse-workspace\\IndianStatesCensusAnalyser\\src\\test\\java\\resources\\DelimiterIncorrect.csv";
    private static String WRONG_FILE_FORMATE="C:\\Users\\abhij\\eclipse-workspace\\IndianStatesCensusAnalyser\\src\\test\\java\\resources\\DelimiterIncorrect.csv";


    StateCensusAnalyser stateCensusAnalyser;
        @Before
        public void setUp()
        {
            stateCensusAnalyser = new StateCensusAnalyser(DATA_CSV_FILE_PATH);
        }

        @Test
        public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnTrue() throws IOException,StateCensusAnalyserException
        {
            int totalRecords=stateCensusAnalyser.loadData();
            Assert.assertEquals(29,totalRecords);
        }

    @Test
    public void givenFileName_whenImproper_shouldThrowException() throws IOException
    {
        try
        {
            stateCensusAnalyser.loadData();
        } catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND,e.exceptionTypeObject);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_whenCorrectButTypeIncorrect_shouldReturnCustomException() throws IOException
    {
        try
        {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(IMPROPER_FILE_TYPE);
            stateCensusAnalyser.loadData();
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND,e.exceptionTypeObject);
        }
    }

    @Test
    public void givenTheStateCensusFile_WhenCorrectButDelimiterIncorrect_shouldReturnCustomException() throws IOException
    {
        try
        {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_DELIMITER1);
            stateCensusAnalyser.loadData();
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND,e.exceptionTypeObject);
        }
    }
    
    @Test
    public void givenFileData_whenIncorrect_shouldThrowException() throws IOException {
        try {
            stateCensusAnalyser = new StateCensusAnalyser(WRONG_FILE_FORMATE);
            stateCensusAnalyser.loadData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

}

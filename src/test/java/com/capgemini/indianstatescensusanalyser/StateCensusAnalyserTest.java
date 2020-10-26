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
    public static String STATE_CODE_FILE = "C:\\Users\\abhij\\eclipse-workspace\\IndianStatesCensusAnalyser\\src\\test\\java\\resources\\StateCode.csv";
    private static String WRONG_STATE_CODE_FILE = "C:\\Users\\abhij\\eclipse-workspace\\IndianStatesCensusAnalyser\\src\\test\\java\\resources\\StateCodeWrongFormat.csv";

    StateCensusAnalyser stateCensusAnalyser;
    CSVStates csvStates = new CSVStates();

    @Before
    public void setUp() {
        stateCensusAnalyser = new StateCensusAnalyser(DATA_CSV_FILE_PATH);
    }

    @Test
    public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnTrue() throws IOException, StateCensusAnalyserException {
        try {
            int totalRecords = stateCensusAnalyser.loadData();
            Assert.assertEquals(29, totalRecords);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenImproper_shouldThrowException() throws IOException {
        try {
            stateCensusAnalyser.loadData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_whenCorrectButTypeIncorrect_shouldReturnCustomException() throws IOException {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(IMPROPER_FILE_TYPE);
            stateCensusAnalyser.loadData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    @Test
    public void givenTheStateCensusFile_WhenCorrectButDelimiterIncorrect_shouldReturnCustomException() throws IOException {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_DELIMITER1);
            stateCensusAnalyser.loadData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
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

    @Test
    public void givenTotalRecordsFromStateCsvDataFile_whenMatch_shouldReturnTrue() throws StateCensusAnalyserException {
        try {
            int totalRecords = csvStates.loadStateCodes(STATE_CODE_FILE);
            Assert.assertEquals(37, totalRecords);
        } catch (Exception e) {
        }
    }

    @Test
    public void givenStateCodeCSVFileNameType_whenImproper_shouldThrowException() throws IOException {
        csvStates.loadStateCodes(WRONG_STATE_CODE_FILE);
    }

    @Test
    public void givenStateCodeCSVFileData_whenIncorrect_shouldThrowException() throws IOException{
        csvStates.loadStateCodes(WRONG_STATE_CODE_FILE);
    }

}

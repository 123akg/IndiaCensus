package com.capgemini.indianstatescensusanalysercom.exception;

public class StateCensusAnalyserException extends Exception
{
	public enum exceptionType
    {
        FILE_NOT_FOUND ,INCORRECT_FILE
    }
    public exceptionType exceptionTypeObject;
    public StateCensusAnalyserException(exceptionType exceptionTypeObject)
    {
        this.exceptionTypeObject=exceptionTypeObject;
    }

}

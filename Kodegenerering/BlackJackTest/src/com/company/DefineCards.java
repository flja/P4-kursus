package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DefineCards {
    int[] values = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};

    public DefineCards()
    {
    }

    public void SetNumericalValue(int faceValue, int value)
    {
        faceValue -= 1;
        if (faceValue >= 0 && faceValue < values.length)
        {
            values[faceValue] = value;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int ConvertFaceValueToNumericalValue(int faceValue)
    {
        faceValue -= 1;
        if (faceValue >= 0 && faceValue < values.length)
        {
            return values[faceValue];
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}

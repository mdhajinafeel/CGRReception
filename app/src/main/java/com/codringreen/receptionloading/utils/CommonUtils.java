package com.codringreen.receptionloading.utils;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CommonUtils {

    public static SpannableString customFontTypeFace(Typeface typeface, CharSequence chars) {
        if (chars == null) {
            return null;
        }
        SpannableString spannableString = new SpannableString(chars);
        spannableString.setSpan(new CustomTypefaceSpan("", typeface), 0, spannableString.length(), 33);
        return spannableString;
    }

    public static SpannableString makeSpannableString(Typeface typeface1, Typeface typeface2, String regularText, String boldText, boolean isSmallText) {
        SpannableString spannableString = null;
        try {
            String str = regularText + boldText;
            int length = regularText.length();
            int length2 = boldText.length() + length;
            SpannableString spannableString2 = new SpannableString(str);
            try {
                spannableString2.setSpan(new CustomTypefaceSpan("", typeface1), 0, length, 33);
                spannableString2.setSpan(new CustomTypefaceSpan("", typeface2), length, length2, 33);
                if (!isSmallText) {
                    return spannableString2;
                }
                spannableString2.setSpan(new RelativeSizeSpan(0.8f), length, length2, 33);
                return spannableString2;
            } catch (Exception e) {
                e = e;
                spannableString = spannableString2;
                e.printStackTrace();

            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return spannableString;
    }

    public static String convertTimeStampToDate(Long milliSeconds, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, new Locale(Locale.getDefault().getLanguage()));
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeInMillis(milliSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Long getCurrentLocalDateTimeStamp() {
        return Calendar.getInstance(TimeZone.getDefault()).getTimeInMillis();
    }

    public static String truncDecimal(double value, double roundingFactor) {
        DecimalFormat decimalFormat;
        if (roundingFactor == 0.0d) {
            decimalFormat = new DecimalFormat("##");
        } else if (roundingFactor == 1.0d) {
            decimalFormat = new DecimalFormat("##.#");
        } else if (roundingFactor == 2.0d) {
            decimalFormat = new DecimalFormat("##.##");
        } else {
            decimalFormat = roundingFactor == 3.0d ? new DecimalFormat("##.###") : null;
        }
        if (decimalFormat == null) {
            return "0";
        }
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(value);
    }

    public static BigDecimal truncateDecimal(double x, int numberofDecimals) {
        if (x > 0.0d) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, RoundingMode.FLOOR);
        }
        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, RoundingMode.CEILING);
    }

    public static BigDecimal roundValue(double value, int numberofDecimals) {
        return BigDecimal.valueOf(value).setScale(numberofDecimals, RoundingMode.HALF_UP);
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static double calculatePieceFormula(double circumference, double length) {
        double pi = Math.PI;
        double truncValue = Math.floor(circumference / pi); // TRUNC equivalent
        double powerValue = Math.pow(truncValue - 5, 2);
        double multiplication = powerValue * 0.7854 * (length - 5) / 1000000;
        return Math.round(multiplication * 1000.0) / 1000.0;
    }
}
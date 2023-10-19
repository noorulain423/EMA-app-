package com.example.survey;

public class Result1 {
    int a1, a2,a3,a4, result;
    int a5,a6,a7,a8,a9,a10;
    String StressLevel;


    public Result1(int a1,int a2,int a3,int a4) {
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;

    }
    public Result1 (int a1,int a2,int a3,int a4,int a5,int a6,int a7,int a8,int a9,int a10){
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        this.a5=a5;
        this.a6=a6;
        this.a7=a7;
        this.a8=a8;
        this.a9=a9;
        this.a10=a10;
    }

    public void setResult(int r1, int r2, int r3, int r4) {
        result= r1+ r2+r3+r4;
    }

    public void setResultPSS10(int a1,int a2,int a3,int a4,int a5,int a6,int a7,int a8,int a9,int a10){
        result = a1+ a2+a3+a4  + a5 + a6+ a7+ a8+ a9+ a10;
    }

    public int getResult() {
        return result;
    }

    public String StressLevelPSS10 (){
        if (result>=0 && result<= 13)
            StressLevel= " Low Stress";
        else if (result>=14 && result <=26)
            StressLevel="Moderate Stress";
                    else if( result>=27 && result <=40)
                        StressLevel= "Highly perceived Stress";

                    return StressLevel;

    }
    public String StresslvlPSS4(){
        if(result>=0 && result<=5)
            StressLevel ="Low Stress";
        else if (result>=6 && result <=11)
            StressLevel="Moderate Stress";
        else if( result>=12 && result <=16)
            StressLevel= "Highly perceived Stress";

        return StressLevel;
    }
}

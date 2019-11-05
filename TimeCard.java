/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjsnuggles;

/**
 *
 * @author trevorneidlinger
 */
public class TimeCard {
    double attendanceBonus;
    double[] hoursWeek;
    double productionHours;
    double paidHours;
    double vacationDay;
    double birthDay;
    boolean[] onTime;
    boolean friMan;


    public TimeCard() {
        attendanceBonus = 0;
        hoursWeek = new double[5];
        productionHours = 0;
        paidHours = 0;
        vacationDay = -1;
        birthDay = -2;
        onTime = new boolean[5];
        friMan = false;
    }

    public double computeTime(double start, double end){
       
        if(start < 6){
            start = 6;
        }

        if(end > 15.5){
            if(end < 15.6){
                end = 15.5;
            }
        }

        double minutes = end - start;

        minutes = (double)Math.round(minutes * 100000d) / 100000d;

        return minutes;
    }

    public void calculateProductionHours(){
        for(int i = 0; i < 5; i++){
            if(hoursWeek[i] > 0) {
                if(i != 4){
                    productionHours = productionHours + hoursWeek[i];
                } else {
                    if(hoursWeek[i]>= 3.5 && hoursWeek[i] <= 4){
                        productionHours = productionHours + 4;
                    } else {
                        productionHours = productionHours + hoursWeek[i];
                    }
                }
            }
        }
    }

    public void calculatePaidHours(double[] val){

        for(int i = 0; i < 5; i++){
            if(hoursWeek[i] < 0){
                paidHours = paidHours + val[i];
                hoursWeek[i] = val[i];
            } else {
                if(i != 4){
                    paidHours = paidHours + hoursWeek[i];
                } else {
                    if(hoursWeek[i]>= 3.5 && hoursWeek[i] <= 4){
                        paidHours = paidHours + 4;
                    } else {
                        paidHours = paidHours + hoursWeek[i];
                    }
                }
            }
        }
    }

    public void calculateAttendanceBonus(){
        for(int i = 0; i < 5; i++){
            if(i != 4){
                if(hoursWeek[i] >= 9 && onTime[i]){
                    attendanceBonus = attendanceBonus + hoursWeek[i];
                }else if(hoursWeek[i] == 0){
                    attendanceBonus = 0;
                    break;
                }
            } else {
                if(hoursWeek[i] >= 3.5 && onTime[i]){
                    if(hoursWeek[i]>= 3.5 && hoursWeek[i] <= 4){
                        
                        attendanceBonus = attendanceBonus + 4;
                        hoursWeek[i] = 4;
                        
                    } else {
                        attendanceBonus = attendanceBonus + hoursWeek[i];
                    }
                } else if(friMan == false && hoursWeek[i] == 0){
                   //do nothing
                   break;
                } else {
                    if(hoursWeek[i] > 0){
                        attendanceBonus = 0;
                    }
                    
                    if(friMan == true){
                        attendanceBonus = 0;
                    }
                }
            }
        }
   
    }
    
    public void resetVariables(){
        attendanceBonus = 0;
        productionHours = 0;
        paidHours = 0;
        vacationDay = -1;
        birthDay = -2;
        friMan = false;
       
        
        for(int i = 0; i < 5; i++){
            hoursWeek[i] = 0;
            onTime[i] = false;
        }
    }
    
    public void convertHours(){
        for(int i = 0; i < 5; i++){
            if(hoursWeek[i] < 0){
                hoursWeek[i] = 8;
            }
        }
    }
}

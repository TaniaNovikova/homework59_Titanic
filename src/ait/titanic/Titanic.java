package ait.titanic;
/*
 * See file train.csv with information about "Titanic" passengers
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Titanic {
    public static void main(String[] args) {
        //BufferedReader считывает данные построчно, а нам нужно посимвольное считывание
        // строк, поэтому передаем ему FileReader("train.csv").
        try (BufferedReader br = new BufferedReader(new FileReader("train.csv"))) {
            String str = br.readLine();
            System.out.println(str);//проверяем, удалось ли считать первую строку
            String[] cells = str.split(",");//разделили на отдельные ячейки данные строки,
            // между которыми стояла запятая
            //   printCells(cells);
            str = br.readLine();
            cells = str.split(",");
            //  printCells(cells);
           /* Task 1 Calculate total fares.
             Task 2 Calculate average fare for 1,2,3 classes of travel.
             Task 3 Calculate total quantity of survived and non survived passengers.
             Task 4 Calculate total quantity of survived and non survived men, women and children(under 18 years old)
            */
            int totalQuantityOfSurvivedPassangers = 0;
            int totalQuantityOfNotSurvivedPassangers = 0;
            double totalFares = 0;
            double firstClassFare = 0;
            double secondClassFare = 0;
            double thirdClassFare = 0;
            int totalPassangers = 0;
            int firstClassCounter = 0;
            int secondClassCounter = 0;
            int thirdClassCounter = 0;
            String male = "male";
            int counterMale = 0;
            String female = "female";
            int counterFemale = 0;
            int counterChildren = 0;
            int counterSurviversChildren = 0;
            int counterSurviversMen = 0;
            int counterSurviversWomen = 0;
            int counterUncnownAge = 0;
            int counterOfAccompanyingPersons = 0;
            int c = 0;
            while (str != null) {
                totalPassangers++;
                if (!(cells[6].isEmpty())) {
                    if (Double.parseDouble(cells[6]) < 18) {
                        counterChildren++;
                        if (Integer.parseInt(cells[1]) == 1) {
                            counterSurviversChildren++;
                        }

                    }
                } else {
                    counterUncnownAge++;
                }

                if (String.valueOf(cells[5]).equalsIgnoreCase(male)) {
                    if (!(cells[6].isEmpty())) {
                        if (Double.parseDouble(cells[6]) >= 18) {
                            counterMale++;
                            if (Integer.parseInt(cells[1]) == 1) {
                                counterSurviversMen++;
                            }

                        }
                    }

                }

                if (String.valueOf(cells[5]).equalsIgnoreCase(female)) {
                    if (!(cells[6].isEmpty())) {
                        if (Double.parseDouble(cells[6]) >= 18) {
                            counterFemale++;
                            if (Integer.parseInt(cells[1]) == 1) {
                                counterSurviversWomen++;
                            }

                        }
                    }

                }


                if (Integer.parseInt(cells[1]) == 1) {
                    totalQuantityOfSurvivedPassangers++;
                } else {
                    totalQuantityOfNotSurvivedPassangers++;
                }
                cells = str.split(",");
                totalFares += Double.parseDouble(cells[10]);
                counterOfAccompanyingPersons+=Integer.parseInt(cells[7]);
                if (Integer.parseInt(cells[2]) == 1) {
                    firstClassCounter++;
                    firstClassFare = Double.parseDouble(cells[10]) + firstClassFare;
                } else if (Integer.parseInt(cells[2]) == 2) {
                    secondClassCounter++;
                    secondClassFare = Double.parseDouble(cells[10]) + secondClassFare;
                } else if (Integer.parseInt(cells[2]) == 3) {
                    thirdClassCounter++;
                    thirdClassFare = Double.parseDouble(cells[10]) + thirdClassFare;
                }

                str = br.readLine();
            }
            double firstClassAverageFare = Math.rint(100.0 * firstClassFare / firstClassCounter) / 100.0;

            double secondClassAverageFare = Math.rint(100.0 *secondClassFare / secondClassCounter)/100.0;
            double thirdClassAverageFare = Math.rint(100.0 *thirdClassFare / thirdClassCounter)/100.0;

            System.out.println("Total fares = " + Math.rint(100.0 *totalFares)/100.0);
            System.out.println("Total passangers = " + (totalPassangers+counterOfAccompanyingPersons));
            System.out.println("Number of accompaning persons is equal "+counterOfAccompanyingPersons);

            System.out.println("First class average fare = " + firstClassAverageFare);
            System.out.println("Second class average fare = " + secondClassAverageFare);
            System.out.println("Third class average fare = " + thirdClassAverageFare);
push            System.out.println("Total quantity of survived passengers is " + totalQuantityOfSurvivedPassangers);
            System.out.println("Total quantity of non survived passengers is " + totalQuantityOfNotSurvivedPassangers);

            System.out.println("Male = " + counterMale);
            System.out.println("Total quantity of survived men = " + counterSurviversMen);
            System.out.println("Total quantity of not survived men = " + (counterMale - counterSurviversMen));

            System.out.println("Female = " + counterFemale);
            System.out.println("Total quantity of survived women = " + counterSurviversWomen);
            System.out.println("Total quantity of not survived women = " + (counterFemale - counterSurviversWomen));

            System.out.println("Cildren = " + counterChildren);
            System.out.println("Total quantity of survived children = " + counterSurviversChildren);
            System.out.println("Total quantity of not survived children = " + (counterChildren - counterSurviversChildren));
            System.out.println("Uncnown age = " + counterUncnownAge);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printCells(String[] cells) {
        for (String s : cells) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }

}



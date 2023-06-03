package com.example.modulecustomer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ticket {
        private int TicketID;
        private String PlateNUM;
        private LocalDateTime EntryTimeStamp;
        private LocalDateTime ExitTimeStamp;
        private int MinuteRate;

        private static List<Ticket> ticketList = new ArrayList<>();

        public Ticket() {
                TicketID = -1;
                PlateNUM = "";
                EntryTimeStamp = LocalDateTime.now();
                MinuteRate = 0;
        }

        public Ticket(String plateNUM) {
                this.PlateNUM = plateNUM;
                this.EntryTimeStamp = LocalDateTime.now();
                MinuteRate = 50;

                Random random = new Random();
                boolean duplicateFound;
                int newTicketID;

                // The following lines of code check if the random number have been generated before.
                do {
                        duplicateFound = false;
                        newTicketID = random.nextInt(90000) + 10000;
                        for (Ticket ticket : ticketList) {
                                if (ticket.getTicketID() == newTicketID) {
                                        duplicateFound = true;
                                        break;
                                }
                        }
                }
                while(duplicateFound);
                this.TicketID = newTicketID;
        }

/*
        public Ticket(String plateNUM) {
                PlateNUM = plateNUM;
                EntryTimeStamp = LocalDateTime.now();

                Random random = new Random();
                TicketID = random.nextInt(90000) + 10000;

        }
*/
        public static Ticket getTicket(String PlateNUM){
                for (Ticket ticket : ticketList) {
                        if (ticket.getPlateNUM().equals(PlateNUM)){
                                return ticket;
                        }
                }
                return new Ticket();
        }
        public static Ticket getTicket(int TicketID){
                for (Ticket ticket : ticketList) {
                        if (ticket.getTicketID()== TicketID){
                                return ticket;
                        }
                }
                return new Ticket();
        }
        public static boolean isCheckedIn(String PlateNUM){
                for (Ticket ticket : ticketList) {
                        if (ticket.getPlateNUM().equals(PlateNUM)){
                                return true;
                        }
                }
                return false;
        }
        public static boolean isCheckedIn(int TicketID){
                for (Ticket ticket : ticketList) {
                        if (ticket.getTicketID() == TicketID){
                                return true;
                        }
                }
                return false;
        }
        public static List<Ticket> getTicketList() {
                return ticketList;
        }

        public static void setTicketList(List<Ticket> ticketList) {
                ticketList = ticketList;
        }

        public static void addToTicketList(Ticket ticket){
                ticketList.add(ticket);
        }

        public void RegenerateTicketID(){
                Random random = new Random();
                TicketID = random.nextInt(90000) + 10000;
        }

        public Duration TimeParked() {
                LocalDateTime currentTime = LocalDateTime.now();
                Duration duration = Duration.between(EntryTimeStamp, currentTime);
                return duration;
        }

        public int getTicketID() {
                return TicketID;
        }

        public String getPlateNUM() {
                return PlateNUM;

        }
        public LocalDateTime getEntryTimeStamp() {
                return EntryTimeStamp;
        }

        public LocalDateTime getExitTimeStamp() {
                return ExitTimeStamp;
        }

        public int getMinuteRate() {
                return MinuteRate;
        }

        public void setMinuteRate(int minuteRate) {
                MinuteRate = minuteRate;
        }

        public void setTicketID(int ticketID) {
                TicketID = ticketID;
        }

        public void setPlateNUM(String plateNUM) {
                PlateNUM = plateNUM;
        }

        public void setEntryTimeStamp(LocalDateTime entryTimeStamp) {
                EntryTimeStamp = entryTimeStamp;
        }

        public void setExitTimeStamp(LocalDateTime exitTimeStamp) {
                ExitTimeStamp = exitTimeStamp;
        }
}

package com.example.modulecustomer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ticket {
        private int TicketID;
        private String PlateNUM;
        private LocalDateTime EntryTimeStamp;
        private LocalDateTime ExitTimeStamp;

        private static List<Ticket> ticketList = new ArrayList<>();
        private static List<String> FreeSpotsList = new ArrayList<>();
        private static boolean isGenratedFreeSpots = false ;


        public Ticket() {
                TicketID = -1;
                PlateNUM = "";
                EntryTimeStamp = LocalDateTime.now();
        }

        public Ticket(String plateNUM) {
                this.PlateNUM = plateNUM;
                this.EntryTimeStamp = LocalDateTime.now();

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

        public static List<String> getFreeSpotsList() {
                return FreeSpotsList;
        }

        public static void setFreeSpotsList(List<String> freeSpotsList) {
                FreeSpotsList = freeSpotsList;
        }

        public static boolean isIsGenratedFreeSpots() {
                return isGenratedFreeSpots;
        }

        public static void setIsGenratedFreeSpots(boolean isGenratedFreeSpots) {
                Ticket.isGenratedFreeSpots = isGenratedFreeSpots;
        }

        public static void GenerateDataFreeSpotsList(){
                FreeSpotsList.add("1");
                FreeSpotsList.add("2");
                FreeSpotsList.add("3");
                FreeSpotsList.add("4");
                FreeSpotsList.add("5");
                FreeSpotsList.add("6");
                FreeSpotsList.add("7");
        }
        public static Ticket getTicket(String PlateNUM){
                for (Ticket ticket : ticketList) {
                        if (ticket.getPlateNUM() == PlateNUM){
                                return ticket;
                        }
                }
                return new Ticket();
        }
        public static boolean isParked(String PlateNUM){
                for (Ticket ticket : ticketList) {
                        if (ticket.getPlateNUM().equals(PlateNUM)){
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

        public long MinutesParked() {
                LocalDateTime currentTime = LocalDateTime.now();
                Duration duration = Duration.between(EntryTimeStamp, currentTime);
                return duration.toMinutes();
        }

        public long HoursParked() {
                LocalDateTime currentTime = LocalDateTime.now();
                Duration duration = Duration.between(EntryTimeStamp, currentTime);
                return duration.toHours();
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

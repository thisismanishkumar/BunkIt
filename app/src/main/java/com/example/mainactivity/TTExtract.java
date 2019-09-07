package com.example.mainactivity;

import com.github.tlaabs.timetableview.SaveManager;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Sticker;

import java.util.ArrayList;
import java.util.HashMap;

public class TTExtract {
    HashMap<Integer, Sticker> stickers = new HashMap<Integer, Sticker>();
    public ArrayList<Schedule> getAllSchedulesInStickers() {
        ArrayList<Schedule> allSchedules = new ArrayList<Schedule>();
        for (int key : stickers.keySet()) {
            for (Schedule schedule : stickers.get(key).getSchedules()) {
                allSchedules.add(schedule);
            }
        }
        return allSchedules;
    }
    public void load(String data) {

        stickers = SaveManager.loadSticker(data);



    }
}

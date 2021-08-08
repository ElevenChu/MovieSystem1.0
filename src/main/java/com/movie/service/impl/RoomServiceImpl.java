package com.movie.service.impl;

import com.movie.bean.Room;
import com.movie.mapper.RoomMapper;
import com.movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomMapper roomMapper;
    @Override
    public List<Room> findRooms() {
        return roomMapper.findRooms();
    }
}

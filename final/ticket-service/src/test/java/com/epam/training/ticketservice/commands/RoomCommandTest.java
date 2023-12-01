package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.dto.RoomDto;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomCommandTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomCommand roomCommand;

    @Test
    void createRoom_Success() {
        String roomName = "Room1";
        int rows = 5;
        int cols = 6;

        String result = roomCommand.createRoom(roomName, rows, cols);

        assertEquals("Room created successfully", result);
        verify(roomService, times(1)).createRoom(roomName, rows, cols);
    }

    @Test
    void updateRoom_Success() {
        String roomName = "Room1";
        int rows = 5;
        int cols = 6;

        String result = roomCommand.updateRoom(roomName, rows, cols);

        assertEquals("Room updated successfully", result);
        verify(roomService, times(1)).updateRoom(roomName, rows, cols);
    }


    @Test
    void deleteRoom_Success() {
        String roomName = "Room1";

        String result = roomCommand.deleteRoom(roomName);

        assertEquals("Room deleted successfully", result);
        verify(roomService, times(1)).deleteRoom(roomName);
    }

    @Test
    void listRooms_Success() {
        List<RoomDto> rooms = Collections.singletonList(new RoomDto("Room1", 5, 6));
        when(roomService.listRooms()).thenReturn(rooms);

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        roomCommand.listRooms();

        assertEquals("Room Name: Room1, Rows: 5, Columns: 6" + System.lineSeparator(), outputStreamCaptor.toString());

        System.setOut(System.out);
    }

}

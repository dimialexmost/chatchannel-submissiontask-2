package com.example.chatchannelinluppgift2.controller;

import com.example.chatchannelinluppgift2.model.Channel;
import com.example.chatchannelinluppgift2.service.ChannelService;
import com.example.chatchannelinluppgift2.ws.ChannelStateSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
@AllArgsConstructor
public class ChatController {

    private ChannelService channelService;
    private ChannelStateSocketHandler channelStateSocketHandler;
    @GetMapping
    public ResponseEntity<List<Channel>> getChannels() {
        return ResponseEntity.ok(channelService.getChannels());
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {

        Channel createdChannel = channelService.create(channel);
        channelStateSocketHandler.broadcast(createdChannel);
        return ResponseEntity.status(201).body(createdChannel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Channel>> deleteChannel(@PathVariable long id) {
        channelService.delete(id);
        return getChannels();
    }
}

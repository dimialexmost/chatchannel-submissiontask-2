package com.example.chatchannelinluppgift2.controller;

import com.example.chatchannelinluppgift2.model.Channel;
import com.example.chatchannelinluppgift2.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
@AllArgsConstructor
public class ChatController {

    private ChannelService channelService;

    @GetMapping
    public ResponseEntity<List<Channel>> getChannels() {
        return ResponseEntity.ok(channelService.getChannels());
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {
        return ResponseEntity.status(201).body(channelService.create(channel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Channel>> deleteChannel(@PathVariable long id) {
        channelService.delete(id);
        return getChannels();
    }
}

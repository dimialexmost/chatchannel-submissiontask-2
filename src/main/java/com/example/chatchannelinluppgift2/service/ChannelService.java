package com.example.chatchannelinluppgift2.service;

import com.example.chatchannelinluppgift2.model.Channel;
import com.example.chatchannelinluppgift2.repository.ChannelRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChannelService {

    private ChannelRepo channelRepo;

    public List<Channel> getChannels(){
        return channelRepo.findAll();
    }
    public Channel create (Channel channel){
        return channelRepo.save(channel);
    }
    public void delete(long id){
        channelRepo.deleteById(id);
    }
}

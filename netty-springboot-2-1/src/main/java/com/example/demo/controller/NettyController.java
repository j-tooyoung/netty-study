package com.example.demo.controller;

import com.example.demo.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nettyServer")
public class NettyController {

    @Autowired
    NettyServer nettyServer;

    @GetMapping("/localAddress")
    public String localAddress() {
        return "nettyServer localAddress: " + nettyServer.getChannel().localAddress().toString();
    }

    @GetMapping("/isOpen")
    public String isOpen() {
        return "nettyServer isOpen: " + nettyServer.getChannel().isOpen();
    }

}

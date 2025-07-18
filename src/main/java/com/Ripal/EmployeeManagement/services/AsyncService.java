package com.Ripal.EmployeeManagement.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

    public void asyncprogramming(){
        Runnable runnable = new Runnable(){
            public void run(){
                for(int i=0; i<10; i++){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    log.info("Process is running... {}",i);
                }
            }
        };
        CompletableFuture.runAsync(runnable);
    }
}

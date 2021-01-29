package com.csw.txlcnservice2;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDistributedTransaction
public class TxLcnService2Application {

    public static void main(String[] args) {
        SpringApplication.run(TxLcnService2Application.class, args);
    }

}

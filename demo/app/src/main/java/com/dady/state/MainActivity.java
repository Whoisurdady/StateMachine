package com.dady.state;

import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dady.statemachine.State;
import com.dady.statemachine.StateMachine;

public class MainActivity extends AppCompatActivity {

    State stateA = new State(){
        @Override
        public void enter() {
            super.enter();
            Toast.makeText(MainActivity.this, "进入状态A", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void exit() {
            super.exit();
        }

        @Override
        public boolean processMessage(Message msg) {
            stateMachine.transitionTo(stateB);
            return true;
        }
    };

    State stateB = new State(){
        @Override
        public void enter() {
            super.enter();
            Toast.makeText(MainActivity.this, "进入状态B", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void exit() {
            super.exit();
        }

        @Override
        public boolean processMessage(Message msg) {
            stateMachine.transitionTo(stateA);
            return true;
        }
    };

    Button changeState;

    StateMachine stateMachine = new StateMachine("STATE", Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildState();
        stateMachine.start();
        changeState = findViewById(R.id.change_state);
        changeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateMachine.sendMessage(0);
            }
        });

    }

    private void buildState() {
        stateMachine.addState(stateA);
        stateMachine.addState(stateB, stateA);
        stateMachine.setInitialState(stateA);
    }
}

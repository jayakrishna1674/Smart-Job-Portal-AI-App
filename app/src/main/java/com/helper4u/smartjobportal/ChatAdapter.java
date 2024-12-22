package com.helper4u.smartjobportal;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatMessage> {

    public ChatAdapter(@NonNull Context context, ArrayList<ChatMessage> chatMessages) {
        super(context, 0, chatMessages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChatMessage chatMessage = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_item, parent, false);
        }

        TextView messageText = convertView.findViewById(R.id.messageText);

        if (chatMessage.getSender().equals("user")) {
            messageText.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.user_message_bg));
        } else {
            messageText.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bot_message_bg));
        }

        messageText.setText(chatMessage.getMessage());
        return convertView;
    }
}

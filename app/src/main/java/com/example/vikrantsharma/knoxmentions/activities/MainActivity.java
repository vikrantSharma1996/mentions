package com.example.vikrantsharma.knoxmentions.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vikrantsharma.knoxmentions.R;
import com.example.vikrantsharma.knoxmentions.model.SlashCommands;
import com.example.vikrantsharma.knoxmentions.utils.SlashPresenter;
import com.example.vikrantsharma.knoxmentions.model.User;
import com.example.vikrantsharma.knoxmentions.utils.UserPresenter;
import com.example.vikrantsharma.knoxmentions.mentions.Autocomplete;
import com.example.vikrantsharma.knoxmentions.mentions.AutocompleteCallback;
import com.example.vikrantsharma.knoxmentions.mentions.AutocompletePolicy;
import com.example.vikrantsharma.knoxmentions.mentions.AutocompletePresenter;
import com.example.vikrantsharma.knoxmentions.mentions.CharPolicy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Autocomplete userAutocomplete;
    private Autocomplete mentionsAutocomplete;
    private Autocomplete maleFemaleAutocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.multi_button).setOnClickListener(this);


        setupMentionsAutocomplete();

    }


    private void setupMentionsAutocomplete() {
        EditText edit = (EditText) findViewById(R.id.multi);
        float elevation = 6f;
        Drawable backgroundDrawable = new ColorDrawable(Color.WHITE);
        AutocompletePolicy policyMentions = new CharPolicy('@'); // Look for @ mentions
        AutocompletePolicy policySlash = new CharPolicy('/'); // Look for / mentions
        AutocompletePresenter<User> presenterMentions = new UserPresenter(this);
        AutocompletePresenter<SlashCommands> presenterSlash = new SlashPresenter(this);
        AutocompleteCallback<User> callbackMentions = new AutocompleteCallback<User>() {
            @Override
            public boolean onPopupItemClicked(Editable editable, User item) {
                // Replace query text with the full name.
                int[] range = CharPolicy.getQueryRange(editable);
                if (range == null) return false;
                int start = range[0];
                int end = range[1];
                String replacement = item.getUsername();
                //String replacement = item.getFullname();
                editable.replace(start, end, replacement);
                // This is better done with regexes and a TextWatcher, due to what happens when
                // the user clears some parts of the text. Up to you.
                editable.setSpan(new StyleSpan(Typeface.BOLD), start, start + replacement.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {
            }
        };
        AutocompleteCallback<SlashCommands> callbackSlash = new AutocompleteCallback<SlashCommands>() {

            @Override
            public boolean onPopupItemClicked(Editable editable, SlashCommands item) {
                // Replace query text with the full name.
                int[] range = CharPolicy.getQueryRange(editable);
                if (range == null) return false;
                int start = range[0];
                int end = range[1];
                String replacement = item.getCommand();
                editable.replace(start, end, replacement);
                // This is better done with regexes and a TextWatcher, due to what happens when
                // the user clears some parts of the text. Up to you.
                editable.setSpan(new StyleSpan(Typeface.BOLD), start, start + replacement.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {
            }
        };

        mentionsAutocomplete = Autocomplete.<User>on(edit)
                .with(elevation)
                .with(backgroundDrawable)
                .with(policyMentions)
                .with(presenterMentions)
                .with(callbackMentions)
                .build();
        mentionsAutocomplete = Autocomplete.<SlashCommands>on(edit)
                .with(elevation)
                .with(backgroundDrawable)
                .with(policySlash)
                .with(presenterSlash)
                .with(callbackSlash)
                .build();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.multi_button:
                ((EditText) findViewById(R.id.multi)).setText("");
                Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

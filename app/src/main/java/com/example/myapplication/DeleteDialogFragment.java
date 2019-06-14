package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class DeleteDialogFragment extends DialogFragment {

    private static FragmentResultListener fragmentResultListener;

    public interface FragmentResultListener {
        void onFragmentResult();
    }

    public static DeleteDialogFragment newInstance(FragmentResultListener listener) {
        DeleteDialogFragment fragment = new DeleteDialogFragment();
        fragmentResultListener = listener;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.delete_dialog_title);
        builder.setMessage(R.string.delete_dialog_message);
        builder.setPositiveButton(R.string.delete_dialog_ok, new DialogButtonClickListener());
        builder.setNegativeButton(R.string.delete_dialog_cancel, new DialogButtonClickListener());
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Activity parent = getActivity();
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    UpdateAndDeletePersonDataActivity activity = new UpdateAndDeletePersonDataActivity();
                    fragmentResultListener.onFragmentResult();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    String msg = "キャンセルしました";
                    Toast.makeText(parent, msg, Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }
}

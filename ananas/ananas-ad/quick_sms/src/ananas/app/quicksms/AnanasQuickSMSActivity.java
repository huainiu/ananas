package ananas.app.quicksms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AnanasQuickSMSActivity extends Activity {

	private TextView mTextMessage;
	private TextView mTextPhoneNum;
	private Button mBtnSend;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.mTextMessage = (TextView) this.findViewById(R.id.editTextMessage);
		this.mTextPhoneNum = (TextView) this
				.findViewById(R.id.editTextPhoneNumber);
		this.mBtnSend = (Button) this.findViewById(R.id.buttonSend);

		this.mBtnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AnanasQuickSMSActivity.this._onClickSend();
			}
		});
		this.mTextPhoneNum.addTextChangedListener(this._getTextWatcher());
		this.mTextMessage.addTextChangedListener(this._getTextWatcher());
	}

	private TextWatcher _getTextWatcher() {
		TextWatcher tw = new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				AnanasQuickSMSActivity.this._onTextChanged();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}
		};
		return tw;
	}

	private void _onTextChanged() {
		CharSequence txt1 = this.mTextMessage.getText();
		CharSequence txt2 = this.mTextPhoneNum.getText();
		boolean isTxtOk = false;
		if (txt1 != null && txt2 != null) {
			if (txt1.length() > 0 && txt2.length() > 0) {
				isTxtOk = true;
			}
		}
		this.mBtnSend.setEnabled(isTxtOk);
	}

	private class DoSend implements DialogInterface.OnClickListener {

		private final String mMessage;
		private final String mNumber;

		public DoSend(String num, String msg) {
			this.mNumber = num;
			this.mMessage = msg;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			AnanasQuickSMSActivity.this.mTextMessage.setText("");
			// AnanasQuickSMSActivity.this.mTextPhoneNum.setText("");

			SmsManager sman = SmsManager.getDefault();
			sman.sendTextMessage(this.mNumber, null, this.mMessage, null, null);

		}
	}

	private void _onClickSend() {

		String msg = this.mTextMessage.getText().toString();
		String num = this.mTextPhoneNum.getText().toString();

		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage(msg);
		builder.setTitle("To:" + num);

		DoSend doSend = new DoSend(num, msg);
		builder.setPositiveButton("OK", doSend);
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();

	}
}

package br.treinar.calculadora;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button[] botoesNumericos;
	private Button[] botoesOperacao;
	private CliqueBotaoNumerico cliqueBotaoNumerico;
	private CliqueBotaoOperacao cliqueBotaoOperacao;
	private EditText display;
	private int n1;
	private int n2;
	private int resultado;
	private boolean limparDisplay = false;

	private static final int SOMAR = 0;
	private static final int SUBTRAIR = 1;
	private static final int MULTIPLICAR = 2;
	private static final int DIVIDIR = 3;

	private int operacaoSelecionada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		botoesNumericos = new Button[10];
		botoesOperacao = new Button[5];

		cliqueBotaoNumerico = new CliqueBotaoNumerico();
		cliqueBotaoOperacao = new CliqueBotaoOperacao();

		display = (EditText) findViewById(R.id.display);
		display.setText("0");

		int[] idsBotoesNumericos = { R.id.btn0, R.id.btn1, R.id.btn2,
				R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
				R.id.btn8, R.id.btn9 };

		for (int i = 0; i < 10; i++) {
			botoesNumericos[i] = (Button) findViewById(idsBotoesNumericos[i]);
			botoesNumericos[i].setOnClickListener(cliqueBotaoNumerico);
		}

		int[] idsBotoesOperacao = { R.id.btnSubtrair, R.id.btnDividir,
				R.id.btnMultiplicar, R.id.btnSomar, R.id.btnIgual };

		for (int i = 0; i < 5; i++) {
			botoesOperacao[i] = (Button) findViewById(idsBotoesOperacao[i]);
			botoesOperacao[i].setOnClickListener(cliqueBotaoOperacao);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("CALCULADORA", "Calculadora parada!");
	}

	private class CliqueBotaoNumerico implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Button btn = (Button) v;

			if (limparDisplay) {
				display.setText("");
				limparDisplay = false;
			}

			display.setText(display.getText().toString()
					+ btn.getText().toString());

		}
	}

	private class CliqueBotaoOperacao implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Button btn = (Button) v;
			if (!btn.getText().equals("=")) {
				limparDisplay = true;
				if (btn.getText().equals("+")) {
					operacaoSelecionada = SOMAR;
				} else if (btn.getText().equals("-")) {
					operacaoSelecionada = SUBTRAIR;
				} else if (btn.getText().equals("x")) {
					operacaoSelecionada = MULTIPLICAR;
				} else if (btn.getText().equals("/")) {
					operacaoSelecionada = DIVIDIR;
				}

				n1 = Integer.parseInt(display.getText().toString());
			} else {
				n2 = Integer.parseInt(display.getText().toString());
				switch (operacaoSelecionada) {
				case SOMAR:
					resultado = n1 + n2;
					break;
				case SUBTRAIR:
					resultado = n1 - n2;
					break;
				case MULTIPLICAR:
					resultado = n1 * n2;
					break;
				case DIVIDIR:
					resultado = n1 / n2;
					break;

				}

				display.setText(Integer.toString(resultado));
			}

		}

	}

}

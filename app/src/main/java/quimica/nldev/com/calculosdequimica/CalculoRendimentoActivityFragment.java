package quimica.nldev.com.calculosdequimica;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;


/**
 * A placeholder fragment containing a simple view.
 */
public class CalculoRendimentoActivityFragment extends Fragment implements View.OnClickListener {
    private Button btnCalcular;
    private EditText edtDescricao;
    private EditText edtMassaMolarReagente;
    private EditText edtMassaMolarProduto;
    private EditText edtMassaReagente;
    private EditText edtMassaProduto;
    private TextView tvResultado;
    private DatabaseHelper helper;

    public CalculoRendimentoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculo_rendimento, container, false);
        btnCalcular = (Button) v.findViewById(R.id.btn_calcular);
        edtDescricao = (EditText) v.findViewById(R.id.edt_descricao);
        edtMassaMolarProduto = (EditText) v.findViewById(R.id.edt_massamolar_produto);
        edtMassaMolarReagente = (EditText) v.findViewById(R.id.edt_massamolar_reagente);
        edtMassaProduto = (EditText) v.findViewById(R.id.edt_massa_produto);
        edtMassaReagente = (EditText) v.findViewById(R.id.edt_massa_reagente);
        tvResultado = (TextView) v.findViewById(R.id.tv_resultado);
        btnCalcular.setOnClickListener(this);
        helper = new DatabaseHelper(v.getContext());

        return v;
    }

    private boolean validaCampos(Context ctx){
        String res = "";
        if(edtMassaReagente.getText().toString().isEmpty())
            res = "Informe a massa do reagente!\n";
        if(edtMassaMolarReagente.getText().toString().isEmpty())
            res = "Informe a massa molar do reagente!\n";
        if(edtMassaProduto.getText().toString().isEmpty())
            res = "Informe a massa do Produto!\n";
        if(edtMassaMolarProduto.getText().toString().isEmpty())
            res = "Informe a massa molar do produto!\n";
        if(!res.isEmpty())
            Toast.makeText(ctx, res,Toast.LENGTH_LONG).show();
        return res.isEmpty();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_calcular:
                if(!validaCampos(v.getContext()))
                    break;
                //(E4/E5)*100/(B4/B5)
                double mp = Double.valueOf(edtMassaProduto.getText().toString());
                double mmp = Double.valueOf(edtMassaMolarProduto.getText().toString());
                double mr = Double.valueOf(edtMassaReagente.getText().toString());
                double mmr = Double.valueOf(edtMassaMolarReagente.getText().toString());
                double result = (mp/mmp)*100/(mr/mmr);
                tvResultado.setText(String.valueOf(result)) ;

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("descricao",edtDescricao.getText().toString());
                Date data = new Date();
                values.put("data", data.getDate());
                values.put("massa_reagente", mr);
                values.put("massa_molar_reagente", mmr);
                values.put("massa_produto", mp);
                values.put("massa_molar_produto", mmp);
                values.put("resultado", result);
        }
    }
}

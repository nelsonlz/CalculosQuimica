package quimica.nldev.com.calculosdequimica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

import quimica.nldev.com.calculosdequimica.quimica.nldev.com.calculosdequimica.domain.Rendimento;


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
                Rendimento r = new Rendimento(v);
                r.setMp(Double.valueOf(edtMassaProduto.getText().toString()));
                r.setMmp(Double.valueOf(edtMassaMolarProduto.getText().toString()));
                r.setMr(Double.valueOf(edtMassaReagente.getText().toString()));
                r.setMmr(Double.valueOf(edtMassaMolarReagente.getText().toString()));
                r.calcular();
                tvResultado.setText(String.valueOf(r.getResult()));
                if(r.salvar(edtDescricao.getText().toString())){
                    Toast.makeText(v.getContext(),"Registro armazenado",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(v.getContext(),"Não foi possivel armazenar o registro!",Toast.LENGTH_SHORT).show();


        }
    }
}

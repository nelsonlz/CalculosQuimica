package quimica.nldev.com.calculosdequimica.quimica.nldev.com.calculosdequimica.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

import quimica.nldev.com.calculosdequimica.DatabaseHelper;

/**
 * Created by nelson on 29/05/2015.
 */
public class Rendimento {
    private String descricao;
    private double mmp;
    private double mp;
    private double mmr;
    private double mr;
    private double result;
    private Date data;
    private DatabaseHelper helper;
    private View view;

    public Rendimento(View v) {
        this.view = v;
        helper = new DatabaseHelper(this.view.getContext());
    }

    public Rendimento(String descricao, double mmp, double mp, double mmr, double mr, double result, Date data) {
        this.descricao = descricao;
        this.mmp = mmp;
        this.mp = mp;
        this.mmr = mmr;
        this.mr = mr;
        this.result = result;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMmp() {
        return mmp;
    }

    public void setMmp(double mmp) {
        this.mmp = mmp;
    }

    public double getMp() {
        return mp;
    }

    public void setMp(double mp) {
        this.mp = mp;
    }

    public double getMmr() {
        return mmr;
    }

    public void setMmr(double mmr) {
        this.mmr = mmr;
    }

    public double getMr() {
        return mr;
    }

    public void setMr(double mr) {
        this.mr = mr;
    }

    public double getResult() {
        return result;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void calcular() {
        this.result = (mp / mmp) * 100 / (mr / mmr);
    }

    public boolean salvar(String descricao) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descricao", descricao);
        Date data = new Date();
        values.put("data", data.getDate());
        values.put("massa_reagente", mr);
        values.put("massa_molar_reagente", mmr);
        values.put("massa_produto", mp);
        values.put("massa_molar_produto", mmp);
        values.put("resultado", result);
        long resultado = db.insert("rendimento", null, values);
        if (resultado != -1) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Rendimento> getAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from rendimento", null);
        cursor.moveToFirst();
        ArrayList<Rendimento> tmp = new ArrayList<Rendimento>();
        String teste = "";
        double t = 0;
        for (int i = 0; i < cursor.getCount(); i++) {
            Rendimento r = new Rendimento(null);
            r.setDescricao(cursor.getString(0));
            r.setData(new Date(cursor.getString(1)));
            r.setMp(cursor.getDouble(2));
            r.setMmp(cursor.getDouble(3));
            r.setMr(cursor.getDouble(4));
            r.setMmr(cursor.getDouble(5));
            r.calcular();
            tmp.add(r);
            cursor.moveToNext();
        }
        return null;
    }
}

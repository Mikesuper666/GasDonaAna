package com.gasdonaana.gasdonaana.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gasdonaana.gasdonaana.Models.RegistradoraModel;
import com.gasdonaana.gasdonaana.Models.TeleModel;
import com.gasdonaana.gasdonaana.R;

public class AlterarRegistFragment extends Fragment{
    private int valor, venda;
    private EditText fragVendaEDTvalor, fragVendaEDTtroco, fragVendaEDTvenda;
    private Spinner fragVendaEDTboy, fragVendadescricao;
    public AlterarRegistFragment() {
        // Required empty public constructor
    }

    private final RegistradoraModel registradoraModel = new RegistradoraModel();
    private final TeleModel teleModel = new TeleModel();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // converte a view para ser retornada o fragmente view e ser usados os componentes
        final View view = inflater.inflate(R.layout.fragment_registradora, container, false);

        //Dados recebidos da activitty anterior
        String coodigo = getArguments().getString("CODIGO");
        int produtCodigo  = getArguments().getInt("PRODINT");
        String prodDesc = getArguments().getString("PRODDESC");
        int vendedor = getArguments().getInt("VENDEDOR");
        String dataVenda = getArguments().getString("DATAVENDA");
        String horarioVenda = getArguments().getString("HOARIOVENDA");
        String horarioEntregue = getArguments().getString("HOARIOENTREGUE");
        int tele = getArguments().getInt("TELE");
        int pagamento = getArguments().getInt("PAGAMENTO");
        int status = getArguments().getInt("STATUS");
        int inteira = getArguments().getInt("INTEIRA");
        int quantidade = getArguments().getInt("QUANTIDADE");
        String total = getArguments().getString("TOTAL");

        //Declarando os componetes
        TextView fragRegistradoraEDTvalor = view.findViewById(R.id.fragRegistradoraEDTvalor);
        TextView fragRegistradoraEDTqtde = view.findViewById(R.id.fragRegistradoraEDTqtde);
        Spinner fraRegistradoraStatus = view.findViewById(R.id.fraRegistradoraStatus);
        EditText fragRegistradoraEDTDesc = view.findViewById(R.id.fragRegistradoraEDTDesc);
        Spinner fragRegistradoraSpinnerTele = view.findViewById(R.id.fragRegistradoraSpinnerTele);
        Spinner fragRegistradoraSpinnerPagamento = view.findViewById(R.id.fragRegistradoraSpinnerPagamento);
        CheckBox fragRegistradoraCheckInteira = view.findViewById(R.id.fragRegistradoraCheckInteira);
        Spinner fragRegistradoraEDTrua = view.findViewById(R.id.fragRegistradoraEDTrua);

        //Adicionando as informações aos componentes
        fragRegistradoraEDTvalor.setText("R$"+total);
        fragRegistradoraEDTqtde.setText("UN"+quantidade);
        fragRegistradoraEDTDesc.setText(prodDesc);
        fraRegistradoraStatus.setSelection(status);
        fragRegistradoraSpinnerTele.setSelection(tele);
        fragRegistradoraSpinnerPagamento.setSelection(pagamento);
        if(inteira == 1){fragRegistradoraCheckInteira.setChecked(true);}
        //fragRegistradoraEDTrua.setSelection(INTEIRAAAAA);


        return view;
    }

}
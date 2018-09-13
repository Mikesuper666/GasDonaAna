package com.gasdonaana.gasdonaana.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


import com.gasdonaana.gasdonaana.Helper.Menssagens;
import com.gasdonaana.gasdonaana.Helper.Preferencias;
import com.gasdonaana.gasdonaana.Models.RegistradoraModel;
import com.gasdonaana.gasdonaana.Models.TeleModel;
import com.gasdonaana.gasdonaana.R;
import com.gasdonaana.gasdonaana.VendaGasActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CadastroVendaFragment extends Fragment{
    private int valor, venda;
    private EditText fragVendaEDTvalor, fragVendaEDTtroco, fragVendaEDTvenda;
    private Spinner fragVendaEDTboy, fragVendadescricao, fragVendaEDTrua, fragVendaEDTbairro;
    private ArrayList<Integer> bairrosContagem = new ArrayList<>();
    private ArrayList<Integer> enderecosContagem = new ArrayList<>();
    private ArrayList<Integer> funcionariosContagem = new ArrayList<>();
    private ArrayList<Integer> idProdutosContagem = new ArrayList<>();
    public CadastroVendaFragment() {
        // Required empty public constructor
    }

    private final RegistradoraModel registradoraModel = new RegistradoraModel();
    private final TeleModel teleModel = new TeleModel();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // converte a view para ser retornada o fragmente view e ser usados os componentes
        final View view = inflater.inflate(R.layout.fragment_cadastro_venda, container, false);

        fragVendadescricao = view.findViewById(R.id.fragVendaEDTdescricao);
        final EditText fragVendaEDTqtde = view.findViewById(R.id.fragVendaEDTqtde);
        fragVendaEDTvalor = view.findViewById(R.id.fragVendaEDTvalor);
        fragVendaEDTtroco = view.findViewById(R.id.fragVendaEDTtroco);
        fragVendaEDTvenda = view.findViewById(R.id.fragVendaEDTvenda);
        final Spinner spinnerTele = view.findViewById(R.id.fragVendaSpinnerTele);
        final Spinner spinnerPag = view.findViewById(R.id.fragVendaSpinnerPagamento);
        CheckBox fragVendaCheckInteira = view.findViewById(R.id.fragVendaCheckInteira);
        final LinearLayout fragVendaLinearEntrega = view.findViewById(R.id.fragVendaLinearEntrega);
        fragVendaEDTrua = view.findViewById(R.id.fragVendaEDTrua);
        final EditText fragVendaEDTnumero = view.findViewById(R.id.fragVendaEDTnumero);
        fragVendaEDTbairro = view.findViewById(R.id.fragVendaEDTbairro);
        fragVendaEDTboy = view.findViewById(R.id.fragVendaEDTboy);
        ImageView fragVendaIMGcancel = view.findViewById(R.id.fragVendaIMGcancel);
        ImageView fragVendaIMGok = view.findViewById(R.id.fragVendaIMGok);

        fragVendaIMGcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });//retorna para a activity anterior

        PreencherDados(Objects.requireNonNull(getArguments().getString("INFORMACOES")));

        spinnerTele.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fragVendaLinearEntrega.setVisibility((position == 0) ? View.GONE : View.VISIBLE);
                registradoraModel.setTele(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });//seta a visibilidade ou não do formulario de endereço

        spinnerPag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                registradoraModel.setPagamento(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragVendaCheckInteira.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                registradoraModel.setInteira((isChecked) ? 1 : 0);
            }
        });


        fragVendaEDTvalor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AtualizarValor(s);
            }
        });

        fragVendaEDTvenda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SomaTroco(s);
            }
        });

        fragVendaIMGok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragVendaEDTvalor.getText().toString().isEmpty()){fragVendaEDTvalor.setError("O valor não pode estar vazio!"); return;}else{registradoraModel.setTotal(fragVendaEDTvalor.getText().toString());}
                if(fragVendaEDTvenda.getText().toString().isEmpty() || venda < valor){fragVendaEDTvenda.setError("Recebimento não pode ser menor que preço"); return;}
                if(fragVendadescricao.getSelectedItemPosition() == 0){
                    fragVendadescricao.setSelection(1);
                    registradoraModel.setProdutoDescricao(fragVendadescricao.getSelectedItem().toString());
                    registradoraModel.setProduto(1);
                }else{
                    registradoraModel.setProdutoDescricao(fragVendadescricao.getSelectedItem().toString());
                    registradoraModel.setProduto(idProdutosContagem.get(fragVendadescricao.getSelectedItemPosition()));
                }
                if(fragVendaEDTqtde.getText().toString().isEmpty()){fragVendaEDTqtde.setError("Não pode estar vazio!"); return;}else{registradoraModel.setQuantidade(Integer.parseInt(fragVendaEDTqtde.getText().toString())); }
                if(spinnerTele.getSelectedItemPosition() == 1){
                    if(fragVendaEDTrua.getSelectedItemPosition() == 0){fragVendaEDTnumero.setError("Adicione o nome da rua"); return;}else{
                        String enderecoArray[] = {enderecosContagem.get(fragVendaEDTrua.getSelectedItemPosition()).toString(),fragVendaEDTrua.getSelectedItem().toString()};
                        teleModel.setEndereco(enderecoArray);}//teleModel.setEndereco(enderecosContagem.get(fragVendaEDTrua.getSelectedItemPosition()));}
                    if(fragVendaEDTnumero.getText().toString().isEmpty()){fragVendaEDTnumero.setError("Adione o número da casa"); return;}else{teleModel.setNumero(Integer.parseInt(fragVendaEDTnumero.getText().toString()));}
                    if(fragVendaEDTbairro.getSelectedItemPosition() == 0){fragVendaEDTnumero.setError("Adicione o bairro"); return;}else{
                        String barrioArray[] = {bairrosContagem.get(fragVendaEDTbairro.getSelectedItemPosition()).toString(), fragVendaEDTbairro.getSelectedItem().toString()};
                        teleModel.setBairro(barrioArray);}
                    if(fragVendaEDTboy.getSelectedItemPosition() == 0){fragVendaEDTnumero.setError("Escolha o entregador"); return;}else{
                        String entregadorArray[] = {funcionariosContagem.get(fragVendaEDTboy.getSelectedItemPosition()).toString(), fragVendaEDTboy.getSelectedItem().toString()};
                        teleModel.setEntregador(entregadorArray);}
                }
                ProcederVenda();
            }
        });

        fragVendaEDTrua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Menssagens.showToastSucesso(enderecosContagem.get(fragVendaEDTrua.getSelectedItemPosition()).toString(),getActivity());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void ProcederVenda(){
        ((VendaGasActivity) getActivity()).Atualizarlista(registradoraModel,teleModel);
    }

    private void AtualizarValor(CharSequence valor){
        fragVendaEDTvenda.setText(valor);
        this.valor = (valor.length()!=0)?Integer.parseInt(fragVendaEDTvenda.getText().toString()):0;
        int troco = (this.venda - this.valor);
        fragVendaEDTtroco.setText(String.valueOf(troco));
    }

    private void SomaTroco(CharSequence venda){
        this.venda = (venda.length()!=0)?Integer.parseInt(venda.toString()):0;
        int troco = (this.venda - this.valor);
        fragVendaEDTtroco.setText(String.valueOf(troco));
    }

    private void PreencherDados(String informacoes) {

        //==============================================================
        //PREENCHER INFORMAÇÕES DE PRODUTOS
        //==============================================================
        String[] produtos = informacoes.split("__");
        ArrayList<String> nomeProdutos = new ArrayList<>();
        nomeProdutos.add("Selecione o produto");
        idProdutosContagem.add(0);
        for (int i = 0; i < produtos.length; ) {

            if (produtos[i].contains("^")) {
                break;
            } else {
                nomeProdutos.add(produtos[(i+1)]+" "+produtos[(i+2)]);
                idProdutosContagem.add(Integer.parseInt(produtos[i].trim()));
                i=i+3;
            }
        }

        //==============================================================
        //PREENCHER INFORMAÇÕES DE dados armazenados em preferencias
        //==============================================================
        Preferencias preferencias = new Preferencias(getActivity());

        //corta a string a cada '__' e insere dentro do array
        //metodo de atualização offline de endereços
        String dadosBairros[] = preferencias.getBairros().split("__");

        ArrayList<String> bairros = new ArrayList<>();
        bairros.add("Selecione o bairro");
        bairrosContagem.add(0);
        for (int i = 0; i < dadosBairros.length; ) {

            if (dadosBairros[i].contains("^")) {
                break;
            } else {
                bairrosContagem.add(Integer.parseInt(dadosBairros[i].trim()));
                bairros.add(dadosBairros[i+1]);
                i+=2;
            }
        }

        //metodo de atualização offline de endereços
        String dadosEnderecos[] = preferencias.getEderecos().split("__");

        List<String> enderecos = new ArrayList<>();
        enderecos.add("Selecione a Rua");
        enderecosContagem.add(0);
        for (int i = 0; i < dadosEnderecos.length; ) {

            if (dadosEnderecos[i].contains("^")) {
                break;
            } else {
                enderecosContagem.add(Integer.parseInt(dadosEnderecos[i].trim()));
                enderecos.add(dadosEnderecos[i+1]);
                i+=2;
            }
        }

        //metodo de atualização offline de endereços
        String dadosFuncionarios[] = preferencias.getFuncionario().split("__");

        List<String> funcionarios = new ArrayList<>();
        funcionarios.add("Selecione o funcionário");
        funcionariosContagem.add(0);
        for (int i = 0; i < dadosFuncionarios.length; ) {

            if (dadosFuncionarios[i].contains("^")) {
                break;
            } else {
                funcionariosContagem.add(Integer.parseInt(dadosFuncionarios[i].trim()));
                funcionarios.add(dadosFuncionarios[i+1]);
                i+=2;
            }
        }
        //==============================================================
        //PREENCHER INFORMAÇÕES DE ADAPTER
        //==============================================================
        ArrayAdapter<String> produtosAdapter =
                new ArrayAdapter<>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, nomeProdutos);
        produtosAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        fragVendadescricao.setAdapter(produtosAdapter);

        ArrayAdapter<String> dataRuas = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, enderecos);
        dataRuas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragVendaEDTrua.setAdapter(dataRuas);

        ArrayAdapter<String> dataBairros = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, bairros);
        dataBairros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragVendaEDTbairro.setAdapter(dataBairros);

        ArrayAdapter<String> dataFuncionarios = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, funcionarios);
        dataFuncionarios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragVendaEDTboy.setAdapter(dataFuncionarios);

    }
}
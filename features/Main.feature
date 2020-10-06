# language: pt
# encoding UTF-8
Funcionalidade: Acesso as compras do Shoestock
  
  Como um usuário
  Eu quero acessar a página do Shoestock

  @chrome-same-session @acessarProduto
  Cenario: AcessarProdutos001;
    Acessar detalhes de um produto
    
    Dado que eu acessei a pagina do shoestock
    Quando eu busco por "sandálias"
    E clico no botao de busca
    Entao aparecem resultados com sandálias

  @chrome-same-session @incluirProduto
  Cenario: IncluirProdutos002;
    Incluir produtos no carrinho
    
    Dado que eu acessei a pagina do shoestock
    Quando eu busco por "sandálias"
    E clico no botao de busca
    E incluo o item 1, com tamanho 34 no carrinho
    Entao o item é incluso ao carrinho
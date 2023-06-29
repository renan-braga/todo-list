package br.com.ifsp.todolist.model;

public enum StatusEnum {

    ABERTO("Aberto"),
    EM_ANDAMENTO("Em andamento"),
    BLOQUEADO("Bloqueado"),
    CONCLUIDO("Concluído");

    private String descricao;

    StatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusEnum getEnumFromString(String status) {
        for (StatusEnum statusEnum: StatusEnum.values()) {
            if (statusEnum.getDescricao().equals(status)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}

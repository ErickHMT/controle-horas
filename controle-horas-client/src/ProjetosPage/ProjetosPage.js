import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import { projetoService, apontamentoHorasService } from '../_services';
import Modal from 'react-modal';

const customStyles = {
    content : {
      top                   : '50%',
      left                  : '50%',
      right                 : 'auto',
      bottom                : 'auto',
      marginRight           : '-50%',
      transform             : 'translate(-50%, -50%)',
      minWidth              : '700px'
    }
  };

class ProjetosPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoaded: false,
            modalIsOpen: false,

            submitted: false,
            totalHoras: '',
            projetoSelecionado: ''
        }

        this.openModal = this.openModal.bind(this);
        this.closeModal = this.closeModal.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.submitHoras = this.submitHoras.bind(this);
    }
    
    componentDidMount() {
        projetoService.getProjetos().then(res => {
            console.log('result', res);
            this.setState({
                isLoaded: true,
                items: res
            })
        })
    }

    openModal(projetoId) {
        console.log('Projeto selecionado: ', projetoId);
        this.setState({
            modalIsOpen: true, 
            projetoSelecionado: projetoId
        });
    }
    
    closeModal() {
        this.setState({
            modalIsOpen: false, 
            projetoSelecionado: ''});
    }

    submitHoras(e) {
        e.preventDefault();
        this.setState({ submitted: true });
        const { totalHoras, projetoSelecionado } = this.state;
        if (totalHoras) {
            apontamentoHorasService.apontarHoras(totalHoras, projetoSelecionado);
        }
    } 

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    render() {
        const { isLoaded, items, submitted, totalHoras } = this.state;

        if(!isLoaded) {
            return <div>Loading...</div>;
        }

        return (
            <div>
                <div className="form-group text-right">
                    <button className="btn btn-outline-primary"><Link to="/login">Logout</Link></button>
                </div>
                <h1>Projetos</h1>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Nome</th>
                            <th scope="col">Total Horas</th>
                            <th scope="col" className="text-center">Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        {items.map(item => (
                            <tr key={item.id}>
                                <td>{item.nome}</td>
                                <td>{item.tempoTotal}</td>
                                <td className="text-center">
                                    <button className="btn btn-primary" onClick={() => this.openModal(item.id)}>Apontar Horas</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <Modal
                    isOpen={this.state.modalIsOpen}
                    onAfterOpen={this.afterOpenModal}
                    onRequestClose={this.closeModal}
                    style={customStyles}
                    contentLabel="Apontar Horas">

                    <h2 ref={subtitle => this.subtitle = subtitle}>Apontar Horas</h2>
                    <form>
                        <div className={'form-group' + (submitted && !totalHoras ? ' has-error' : '')}>
                            <label htmlFor="totalHoras">Total horas: </label>
                            <input type="time" className="form-control" name="totalHoras" value={totalHoras} onChange={this.handleChange} />
                            {submitted && !totalHoras &&
                                <div className="help-block">Informe tempo gasto na atividade</div>
                            }
                        </div>
                    </form>
                    <br/>
                    <div className="form-group text-right">
                        <button type="button" className="btn btn-danger" onClick={this.closeModal}>Fechar</button>
                        <button type="submit" className="btn btn-primary" onClick={this.submitHoras}>confirmar</button>
                    </div>
                </Modal>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const { users, authentication } = state;
    const { user } = authentication;
    return {
        user,
        users
    };
}

const connectedProjetosPage = connect(mapStateToProps)(ProjetosPage);
export { connectedProjetosPage as ProjetosPage };
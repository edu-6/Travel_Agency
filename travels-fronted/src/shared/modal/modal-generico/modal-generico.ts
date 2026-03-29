import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-modal-generico',
  imports: [],
  templateUrl: './modal-generico.html'
})
export class ModalGenerico {
  @Input({ required: true })
  mensaje!: string;

  @Output()
  confirmationExecuter = new EventEmitter<void>();

  ejecutarConfirmacion(): void {
    this.confirmationExecuter.emit();
  }


}

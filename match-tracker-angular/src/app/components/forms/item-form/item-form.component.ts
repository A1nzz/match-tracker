import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Item } from '../../../models/models';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-item-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.scss'],
})
export class ItemFormComponent {
  itemForm: FormGroup;
  isEditMode: boolean = false;

  constructor(
    private readonly fb: FormBuilder,
    public dialogRef: MatDialogRef<ItemFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Item | null
  ) {
    // Инициализация формы
    this.itemForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      logoUrl: ['', Validators.required],
      cost: [0, [Validators.required, Validators.min(0)]],
    });

    // Если передан предмет (режим редактирования), заполняем форму
    if (data) {
      this.isEditMode = true;
      this.itemForm.patchValue(data);
    }
  }

  // Сохранить предмет
  saveItem(): void {
    if (this.itemForm.valid) {
      this.dialogRef.close(this.itemForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}